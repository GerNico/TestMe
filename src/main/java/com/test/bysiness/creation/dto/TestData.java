package com.test.bysiness.creation.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestData {
    private long id;
    private String testDescription;
    private List<QuestionData> questions = new ArrayList<>();
}
