package com.test.bysiness.questions;

import com.test.bysiness.Answer;
import com.test.bysiness.Question;
import com.test.bysiness.Topic;
import com.test.bysiness.answers.AnswerImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SingleOptionQuestionTest {

    private Question<Integer> question;

    @Before
    public void init() {
        String theQuestion = "Are you gay?";
        Topic theTopic = new Topic(3, "Sexual");
        List<String> options = Arrays.asList("Yes", "No");
        Integer score = 2;
        Answer<Integer> correctAnswer = new AnswerImpl<>(1);
        question = new QuestionWithOptions<>(theQuestion, options, theTopic, correctAnswer, score);
    }

    @Test
    public void questionCreated() {
        assertNotNull(question);
        assertNotNull(question.getQuestion());
        assertNotNull(question.getTopic());
        assertFalse(question.isAnswerCorrect());
    }

    @Test
    public void correctAnswer() {
        Answer<Integer> answer = new AnswerImpl<>(1);
        assertFalse(question.isAnswerCorrect());
        question.giveAnswer(answer);
        assertTrue(question.isAnswerCorrect());
    }

    @Test
    public void wrongAnswer() {
        Answer<Integer> answer = new AnswerImpl<>(0);
        assertFalse(question.isAnswerCorrect());
        question.giveAnswer(answer);
        assertFalse(question.isAnswerCorrect());
    }

    @Test
    public void immutableAnswer() {
        Answer<Integer> answer = new AnswerImpl<>(0);
        assertFalse(question.isAnswerCorrect());
        question.giveAnswer(answer);
        Answer<Integer> newAnswer = new AnswerImpl<>(1);
        question.giveAnswer(newAnswer);
        assertFalse(question.isAnswerCorrect());
    }

}