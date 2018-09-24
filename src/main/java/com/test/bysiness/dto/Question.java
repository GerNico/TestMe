package com.test.bysiness.dto;

import com.test.bysiness.utilities.QuestionType;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Question {
    private long id;
    private String question;
    private String answerForNoOptions;
    private QuestionType type;
    private Set<Option> options = new HashSet<>();
}
