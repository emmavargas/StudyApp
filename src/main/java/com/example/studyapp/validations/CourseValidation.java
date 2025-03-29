package com.example.studyapp.validations;

import com.example.studyapp.services.CourseService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class CourseValidation implements ConstraintValidator<IsExistsCourse, String> {

    private final CourseService courseService;

    public CourseValidation(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !courseService.existsByTitleCourse(s);
    }
}
