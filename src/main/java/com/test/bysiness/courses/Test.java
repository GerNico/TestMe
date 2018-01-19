package com.test.bysiness.courses;

import com.test.bysiness.Question;

import java.util.List;

public interface Test {

    Integer getScore();

    Boolean isPassed();

    Boolean isCompleted();

    Integer getThreshold();

    Integer maxScore();

    List<Question> getAllQuestions();
}
