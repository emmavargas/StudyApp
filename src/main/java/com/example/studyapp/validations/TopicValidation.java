package com.example.studyapp.validations;

import com.example.studyapp.services.CourseService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class TopicValidation implements ConstraintValidator<IsExistsTopic, String> {

    private final CourseService courseService;

    public TopicValidation(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !courseService.existsByTitleTopic(s);
    }
}
