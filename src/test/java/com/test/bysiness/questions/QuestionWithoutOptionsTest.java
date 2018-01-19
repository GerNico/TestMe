package com.test.bysiness.questions;

import com.test.bysiness.Answer;
import com.test.bysiness.Question;
import com.test.bysiness.Topic;
import com.test.bysiness.answers.AnswerImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionWithoutOptionsTest {
    private Question<String> question;

    @Before
    public void init() {
        String theQuestion = "Who was lord of the ring?";
        Topic theTopic = new Topic(2, "Cinema");
        Answer<String> correctAnswer = new AnswerImpl<>("Sauron");
        Integer score = 2;
        question = new QuestionWithoutOptions<>(theQuestion, theTopic, correctAnswer, score);
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
        Answer<String> answer = new AnswerImpl<>("Sauron");
        assertFalse(question.isAnswerCorrect());
        question.giveAnswer(answer);
        assertTrue(question.isAnswerCorrect());
    }

    @Test
    public void wrongAnswer() {
        Answer<String> answer = new AnswerImpl<>("Frodo");
        assertFalse(question.isAnswerCorrect());
        question.giveAnswer(answer);
        assertFalse(question.isAnswerCorrect());
    }

    @Test
    public void immutableAnswer() {
        Answer<String> answer = new AnswerImpl<>("Frodo");
        assertFalse(question.isAnswerCorrect());
        question.giveAnswer(answer);
        Answer<String> newAnswer = new AnswerImpl<>("Sauron");
        question.giveAnswer(newAnswer);
        assertFalse(question.isAnswerCorrect());
    }
}