package com.test.bysiness;

import java.util.List;

public interface Test {

    Integer getId();

    Integer getScore();

    Boolean isPassed();

    Boolean isCompleted();

    Integer getThreshold();

    Integer maxScore();

    List<Question> getAllQuestions();

    Topic getMainTopic();

    Question get(Integer index);

}
