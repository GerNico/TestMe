package com.test.bysiness.creation.dto;

import com.test.utilities.QuestionType;
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
