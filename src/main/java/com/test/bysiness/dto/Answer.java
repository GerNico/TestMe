package com.test.bysiness.dto;

import lombok.Data;

import java.util.List;

@Data
public class Answer {
    Long answerId;
    Long questionId;
    boolean isAnswered;
    boolean isCorrect;
    List<Integer> selectedOptions;
    String givenAnswer;
}
