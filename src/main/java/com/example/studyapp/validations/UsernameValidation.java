package com.example.studyapp.validations;

import com.example.studyapp.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsernameValidation implements ConstraintValidator<IsExistsUsername, String> {


    @Autowired
    private  UserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if(userService ==null) return true;
        System.out.println("entro");
        return !userService.existsByUsername(s);

    }
}
