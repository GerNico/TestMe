package com.test.bysiness.questions;

import com.test.bysiness.Answer;
import com.test.bysiness.Question;
import com.test.bysiness.Topic;
import com.test.bysiness.answers.AnswerImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SingleOptionQuestionTest {

    private Question<Integer> question;

    @Before
    public void init() {
        Answer<Integer> correctAnswer = new AnswerImpl<>(1);
        question = new QuestionWithOptions<>("Are you gay?",
                Arrays.asList("Yes", "No"), new Topic(1, "Sexual"), correctAnswer);
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
        Answer<Integer> answer=new AnswerImpl<>(1);
        assertFalse(question.isAnswerCorrect());
        question.giveAnswer(answer);
        assertTrue(question.isAnswerCorrect());
    }

    @Test
    public void wrongAnswer() {
        Answer<Integer> answer=new AnswerImpl<>(0);
        assertFalse(question.isAnswerCorrect());
        question.giveAnswer(answer);
        assertFalse(question.isAnswerCorrect());
    }

    @Test
    public void immutableAnswer() {
        Answer<Integer> answer=new AnswerImpl<>(0);
        assertFalse(question.isAnswerCorrect());
        question.giveAnswer(answer);
        Answer<Integer> newAnswer=new AnswerImpl<>(1);
        question.giveAnswer(newAnswer);
        assertFalse(question.isAnswerCorrect());
    }

}