package com.management.validator;


import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = QuestionValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface ValidQuestion {

    String message() default "Invalid question data";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

