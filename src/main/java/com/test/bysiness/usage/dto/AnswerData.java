package com.test.bysiness.usage.dto;

import lombok.Data;

import java.util.List;

@Data
public class AnswerData {
    Long answerId;
    Long questionId;
    Long passedTestId;
    boolean isCorrect;
    List<Long> selectedOptions;
    String givenAnswer;
}
