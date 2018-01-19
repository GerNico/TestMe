package com.test.bysiness.questions;

import com.test.bysiness.Answer;
import com.test.bysiness.Question;
import com.test.bysiness.Topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionWithOptions<T> implements Question<T> {

    private int id;
    private String question;
    private List<String> options;
    private Topic questionTopic;
    private Answer<T> correctAnswer;
    private Answer<T> yourAnswer;
    private int questionScore;
    private boolean isAnswered;

    QuestionWithOptions(String question, List<String> options, Topic questionTopic, Answer<T> correctAnswer, Integer questionScore) {
        this.question = question;
        this.options = options;
        this.questionTopic = questionTopic;
        this.correctAnswer = correctAnswer;
        this.questionScore = questionScore;
        this.isAnswered = false;
    }

    public Question setId(int id) {
        this.id = id;
        return this;
    }

    public Integer getQuestionScore() {
        return questionScore;
    }

    public Question setQuestionTopic(Topic questionTopic) {
        this.questionTopic = questionTopic;
        return this;
    }

    public Answer<T> getCorrectAnswer() {
        return correctAnswer;
    }

    public Answer<T> getYourAnswer() {
        return yourAnswer;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public List<String> getOptions() {
        return new ArrayList<>(options);
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public Topic getTopic() {
        return questionTopic;
    }

    @Override
    public Boolean isAnswerCorrect() {
        return yourAnswer != null && yourAnswer.equals(correctAnswer);
    }

    @Override
    public Boolean giveAnswer(Answer<T> answer) {
        if (yourAnswer == null) {
            this.yourAnswer = answer;
            return true;
        }
        isAnswered = true;
        return false;
    }

    @Override
    public Integer getPossibleScore() {
        return questionScore;
    }

    @Override
    public Boolean isAnswered() {
        return isAnswered;
    }

    @Override
    public Question<T> duplicate() {
        return new QuestionWithOptions<>(
                question, new ArrayList<>(options), questionTopic, correctAnswer.duplicate(), questionScore);
    }
}
