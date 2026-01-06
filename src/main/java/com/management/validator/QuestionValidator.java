package com.management.validator;
import java.util.List;

import com.management.enm.QuestionType;
import com.management.model.OptionRequest;
import com.management.model.QuestionRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class QuestionValidator
        implements ConstraintValidator<ValidQuestion, QuestionRequest> {

    @Override
    public boolean isValid(
            QuestionRequest request,
            ConstraintValidatorContext context) {

        if (request == null) return true;

        QuestionType type = request.getQuestionType();
        List<OptionRequest> options = request.getOptions();

        context.disableDefaultConstraintViolation();

        if (type == QuestionType.TEXT) {
            if (options != null && !options.isEmpty()) {
                context.buildConstraintViolationWithTemplate(
                        "TEXT question must not have options")
                        .addPropertyNode("options")
                        .addConstraintViolation();
                return false;
            }
            return true;
        }

        if (options == null || options.size() < 2) {
            context.buildConstraintViolationWithTemplate(
                    "At least 2 options are required")
                    .addPropertyNode("options")
                    .addConstraintViolation();
            return false;
        }

        long correctCount = options.stream()
                .filter(OptionRequest::isCorrect)
                .count();

        if (type == QuestionType.MCQ) {
            if (correctCount < 1) {
                context.buildConstraintViolationWithTemplate(
                        "MCQ must have at least one correct option")
                        .addPropertyNode("options")
                        .addConstraintViolation();
                return false;
            }
        }

        if (type == QuestionType.TRUE_FALSE) {
            if (options.size() != 2 || correctCount != 1) {
                context.buildConstraintViolationWithTemplate(
                        "TRUE_FALSE must have exactly 2 options and 1 correct")
                        .addPropertyNode("options")
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
