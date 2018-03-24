package com.test.bysiness.questions;

public enum QuestionType {

    WITH_OPTIONS("options"),
    WITHOUT_OPTIONS("no options"),
    SEQUENTIAL("sequence");

    private String value;

    QuestionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
