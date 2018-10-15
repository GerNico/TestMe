package com.test.bysiness.dto;

import com.test.bysiness.utilities.QuestionType;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class QuestionData {
    private long id;
    private String question;
    private String answerForNoOptions;
    private QuestionType type;
    private Set<OptionData> options = new HashSet<>();
}
