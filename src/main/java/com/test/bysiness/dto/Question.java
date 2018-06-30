package com.test.bysiness.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class Question {
    private long id;
    private String question;
    private String answerForNoOptions;
    private String type;
    private Set<Option> options = new HashSet<>();
}
