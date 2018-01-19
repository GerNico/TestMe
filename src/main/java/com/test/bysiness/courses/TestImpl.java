package com.test.bysiness.courses;

import com.test.bysiness.Question;
import com.test.bysiness.Test;
import com.test.bysiness.Topic;

import java.util.ArrayList;
import java.util.List;

public class TestImpl implements Test {

    private List<Question> questions;
    private String Author;
    private int threshold;
    private int id;

    public TestImpl(List<Question> questions,Integer threshold) {
        this.questions = questions;
        this.threshold=threshold;
        this.id=0;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getAuthor() {
        return Author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public Integer getScore() {
        return questions.stream()
                .filter(Question::isAnswerCorrect)
                .map(Question::getPossibleScore)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    @Override
    public Boolean isPassed() {
        int score=questions.stream()
                .filter(Question::isAnswerCorrect)
                .map(Question::getPossibleScore)
                .mapToInt(Integer::valueOf)
                .sum();
        return score>=getThreshold();
    }

    @Override
    public Boolean isCompleted() {
        return questions.stream()
                .anyMatch(question -> !question.isAnswered());
    }

    @Override
    public Integer getThreshold() {
        return threshold;
    }

    @Override
    public Integer maxScore() {
        return questions.stream()
                .map(Question::getPossibleScore)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    @Override
    public List<Question> getAllQuestions() {
        List<Question> clonedList=new ArrayList<>(questions.size());
        for (Question question:questions) {
            clonedList.add((Question) question.duplicate());
        }
        return clonedList;
    }

    @Override
    public Topic getMainTopic() {

        return null;
    }

    @Override
    public Question get(Integer index) {
        return questions.get(index);
    }
}
