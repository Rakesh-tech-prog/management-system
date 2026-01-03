package com.management.enm;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum QuestionType {
    MCQ,
    TRUE_FALSE,
    TEXT;
    
    @JsonCreator
    public static QuestionType from(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("question Type must not be blank");
        }
        return QuestionType.valueOf(value.trim().toUpperCase());
    }
}

