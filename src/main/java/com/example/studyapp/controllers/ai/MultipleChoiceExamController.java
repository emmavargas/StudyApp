package com.example.studyapp.controllers.ai;

import com.example.studyapp.dtos.CourseExamDto;
import com.example.studyapp.dtos.examChoice.ExamChoiceDto;
import com.example.studyapp.services.CourseService;
import com.example.studyapp.services.ai.MultipleChoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/user/courses/")
public class MultipleChoiceExamController {

    private final MultipleChoiceService multipleChoiceService;
    private final CourseService courseService;

    private final Map<Long, Bucket> buckets = new ConcurrentHashMap<>();
    public MultipleChoiceExamController(MultipleChoiceService multipleChoiceService, CourseService courseService) {
        this.multipleChoiceService= multipleChoiceService;
        this.courseService = courseService;
    }

    @PostMapping("/{id}/generar-examen")

    public ResponseEntity<?> generation(@RequestBody CourseExamDto courseExamDto, @PathVariable Long id) throws JsonProcessingException {
        Long userId = courseService.getUserId();
        Bucket bucket = resolveBucket(userId);
        System.out.println(buckets);
        System.out.println(buckets.size());
        if(bucket.tryConsume(1)){
            try {
                ExamChoiceDto examChoiceDto = multipleChoiceService.getMultipleChoice(id, courseExamDto);
                System.out.println(multipleChoiceService.getMultipleChoice(id, courseExamDto));
                return ResponseEntity.ok(examChoiceDto);
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
                return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(e.getMessage());
            }
        }else{
            Map<String,String> map = Map.of("message","Termino el limite de respuestas");
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(map);
        }

    }
    private Bucket resolveBucket(Long userId) {
        return buckets.computeIfAbsent(userId, key -> {
            Bandwidth limit = Bandwidth.classic(4, Refill.intervally(4, Duration.ofMinutes(5)));
            return Bucket.builder().addLimit(limit).build();
        });
    }
}
