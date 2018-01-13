package com.test.bysiness;

import java.util.List;

public interface Question<T> {

    Integer getId();

    List<String> getOptions();

    String getQuestion();

    Topic getTopic();

    Boolean isAnswerCorrect();

    Boolean giveAnswer(Answer<T> answer);

}
