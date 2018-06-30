package com.test.bysiness.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Option {
    private long id;
    private String text;
    private boolean isCorrect;
    private boolean isSequenceBased;
    private long numberInSequence;
}
