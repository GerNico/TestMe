package com.test.utilities;

public enum Messages {

    USER_ALREADY_EXISTS("User with this login already exists"),
    USER_EMAIL_ALREADY_EXISTS("This email is already in use"),
    USER_WAS_NOT_CREATED("User was not created"),
    USER_WAS_CREATED("User was successfully created"),
    LOGIN_IS_FREE("Login is free to use"),
    EMAIL_IS_FREE("Email is free to use"),

    COURSE_DOES_NOT_EXIST("CourseData does not exists"),
    TEST_NOT_EXIST("TestData does not exist"),
    QUESTION_SAVED("QuestionData saved"),
    TEST_ADDED("TestData have been add");

    private String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
