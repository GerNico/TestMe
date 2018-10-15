package com.test.bysiness.dto;

import lombok.Data;

@Data
public class OptionData {
    private long id;
    private String text;
    private boolean isCorrect;
    private boolean isSequenceBased;
    private long numberInSequence;
}
