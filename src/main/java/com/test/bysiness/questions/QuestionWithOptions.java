package com.test.bysiness.questions;

import com.test.bysiness.Answer;
import com.test.bysiness.Question;
import com.test.bysiness.Topic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionWithOptions<T> implements Question<T> {

    private int id;
    private String question;
    private List<String> options;
    private Topic questionTopic;
    Answer<T> correctAnswer;
    Answer<T> yourAnswer;

    QuestionWithOptions(String question, List<String> options, Topic questionTopic, Answer<T> correctAnswer) {
        this.question = question;
        this.options = options;
        this.questionTopic = questionTopic;
        this.correctAnswer = correctAnswer;
    }

    public Question setId(int id) {
        this.id = id;
        return this;
    }

    public Question setQuestionTopic(Topic questionTopic) {
        this.questionTopic = questionTopic;
        return this;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public List<String> getOptions() {
        List<String> newList = new ArrayList<>();
        Collections.copy(newList, options);
        return newList;
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
        if (yourAnswer==null){
        this.yourAnswer=answer;
        return true;
        }
        return false;
    }
}
