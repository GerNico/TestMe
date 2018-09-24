package com.test.bysiness.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Test {
    private long id;
    private String testDescription;
    private List<Question> questions = new ArrayList<>();
}
