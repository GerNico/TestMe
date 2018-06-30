package com.test.bysiness.dto;

import com.test.bysiness.entities.QuestionEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Test {
    private long id;
    private String testDescription;
    private List<Question> questions = new ArrayList<>();
}
