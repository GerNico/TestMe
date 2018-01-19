package com.test.bysiness.questions;

import com.test.bysiness.Answer;
import com.test.bysiness.Topic;

import java.util.Collections;
import java.util.List;

public class QuestionWithoutOptions<T> extends QuestionWithOptions<T> {

    QuestionWithoutOptions(String question, Topic questionTopic, Answer<T> correctAnswer, Integer questionScore) {
        super(question, Collections.emptyList(), questionTopic, correctAnswer, questionScore);
    }

    @Override
    public List<String> getOptions() {
        return Collections.emptyList();
    }

    @Override
    public Boolean isAnswerCorrect() {
        return this.getYourAnswer() != null && this.getCorrectAnswer().get().equals(this.getYourAnswer().get());
    }
}
