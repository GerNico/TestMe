package com.test.bysiness.utilities;

public enum Messages {

    USER_ALREADY_EXISTS("User with this login already exists"),
    USER_DOES_NOT_EXIST("User does not exists exists"),
    LOGIN_IS_FREE("Login is free to use"),

    COURSE_DOES_NOT_EXIST("Course does not exists"),
    TEST_NOT_EXIST("Test does not exist"),
    QUESTION_SAVED("Question saved"),
    TEST_ADDED("Test have been add");

    private String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
