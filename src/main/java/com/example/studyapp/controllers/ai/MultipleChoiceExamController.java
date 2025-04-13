package com.example.studyapp.controllers.ai;

import com.example.studyapp.dtos.CourseExamDto;
import com.example.studyapp.dtos.examChoice.ExamChoiceDto;
import com.example.studyapp.services.ai.MultipleChoiceExamAiServiceImpl;
import com.example.studyapp.services.ai.MultipleChoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/courses/")
public class MultipleChoiceExamController {

    private final MultipleChoiceService multipleChoiceService;

    public MultipleChoiceExamController(MultipleChoiceService multipleChoiceService) {
        this.multipleChoiceService= multipleChoiceService;
    }

    @PostMapping("/{id}/generar-examen")
    public ExamChoiceDto generation(@RequestBody CourseExamDto courseExamDto, @PathVariable Long id) throws JsonProcessingException {
        //System.out.println(courseExamDto.toString());
        System.out.println("Solicitud recibida en generateExam para id: " + id);
        return multipleChoiceService.getMultipleChoice(id, courseExamDto);
    }

}
