package com.example.studyapp.validations;

import com.example.studyapp.services.CourseService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicValidation implements ConstraintValidator<IsExistsTopic, String> {

    @Autowired
    private  CourseService courseService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
       if(courseService ==null) return true;
       return !courseService.existsByTitleTopic(s);
    }
}
