package com.example.studyapp.validations;

import com.example.studyapp.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UsernameValidation implements ConstraintValidator<IsExistsUsername, String> {


    private final UserService userService;

    public UsernameValidation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("entro");
        return !userService.existsByUsername(s);

    }
}
