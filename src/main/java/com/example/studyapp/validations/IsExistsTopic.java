package com.example.studyapp.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TopicValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface IsExistsTopic {

    String message() default "Ya existe un tema con ese nombre. Use otro nombre.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
