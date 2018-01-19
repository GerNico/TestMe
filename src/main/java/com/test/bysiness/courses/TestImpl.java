package com.test.bysiness.courses;

import com.test.bysiness.Question;

import java.util.ArrayList;
import java.util.List;

public class TestImpl implements Test {

    private List<Question> questions;
    private String Author;
    private int threshold;

    public TestImpl(List<Question> questions,Integer threshold) {
        this.questions = questions;
        this.threshold=threshold;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getAuthor() {
        return Author;
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
}
