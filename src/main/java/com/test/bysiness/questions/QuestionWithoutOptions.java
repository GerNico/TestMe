package com.test.bysiness.questions;

import com.test.bysiness.Answer;
import com.test.bysiness.Topic;

import java.util.Collections;
import java.util.List;

public class QuestionWithoutOptions<T> extends QuestionWithOptions<T> {

    QuestionWithoutOptions(String question, Topic questionTopic, Answer<T> correctAnswer) {
        super(question, Collections.emptyList(), questionTopic, correctAnswer);
    }

    @Override
    public List<String> getOptions() {
        return Collections.emptyList();
    }

    @Override
    public Boolean isAnswerCorrect() {
        return this.yourAnswer != null && this.correctAnswer.get().equals(this.yourAnswer.get());
    }
}
