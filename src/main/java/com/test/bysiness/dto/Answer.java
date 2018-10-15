package com.test.bysiness.dto;

import lombok.Data;

import java.util.List;

@Data
public class Answer {
    Long answerId;
    Long questionId;
    Long passedTestId;
    boolean isCorrect;
    List<Long> selectedOptions;
    String givenAnswer;
}
