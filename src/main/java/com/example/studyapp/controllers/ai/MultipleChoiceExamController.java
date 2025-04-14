package com.example.studyapp.controllers.ai;

import com.example.studyapp.dtos.CourseExamDto;
import com.example.studyapp.dtos.examChoice.ExamChoiceDto;
import com.example.studyapp.services.ai.MultipleChoiceExamAiServiceImpl;
import com.example.studyapp.services.ai.MultipleChoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/courses/")
public class MultipleChoiceExamController {

    private final MultipleChoiceService multipleChoiceService;

    public MultipleChoiceExamController(MultipleChoiceService multipleChoiceService) {
        this.multipleChoiceService= multipleChoiceService;
    }

    @PostMapping("/{id}/generar-examen")
    public ResponseEntity<?> generation(@RequestBody CourseExamDto courseExamDto, @PathVariable Long id) throws JsonProcessingException {
        //System.out.println(courseExamDto.toString());
        try {
            ExamChoiceDto examChoiceDto = multipleChoiceService.getMultipleChoice(id, courseExamDto);
            System.out.println(multipleChoiceService.getMultipleChoice(id, courseExamDto));
            return ResponseEntity.ok(examChoiceDto);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(e.getMessage());
        }
    }

}
