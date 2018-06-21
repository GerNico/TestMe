package com.test.bysiness.dto;


public interface Question {

    boolean isAnswered();
    int score();
    TestDTO getParentTest();
}
