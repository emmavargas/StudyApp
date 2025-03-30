package com.example.studyapp.controllers.ai;

import com.example.studyapp.dtos.CourseExamDto;
import com.example.studyapp.services.ai.MultipleChoiceExamService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/courses/")
public class MultipleChoiceExamController {

    private final MultipleChoiceExamService multipleChoiceExamService;

    public MultipleChoiceExamController(MultipleChoiceExamService multipleChoiceExamService) {
        this.multipleChoiceExamService = multipleChoiceExamService;
    }

    @PostMapping("/{id}/generar-examen")
    public String generation(@RequestBody CourseExamDto courseExamDto, @PathVariable Long id) {
        //System.out.println(courseExamDto.toString());
        return multipleChoiceExamService.generateChoiceExam(id, courseExamDto);
    }

}
