package com.test.bysiness.questions;

import com.test.bysiness.Answer;
import com.test.bysiness.Question;
import com.test.bysiness.Topic;
import com.test.bysiness.answers.AnswerImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MultiOptionQuestionTest {
    private Question<List<Integer>> question;

    @Before
    public void init() {
        String theQuestion = "select the odd numbers";
        Topic theTopic = new Topic(1, "Arithmetic");
        List<String> options = Arrays.asList("0", "2", "5", "8");
        Integer score = 2;
        Answer<List<Integer>> correctAnswer = new AnswerImpl<>(Arrays.asList(0, 1, 3));
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
        Answer<List<Integer>> answer = new AnswerImpl<>(Arrays.asList(0, 1, 3));
        assertFalse(question.isAnswerCorrect());
        question.giveAnswer(answer);
        assertTrue(question.isAnswerCorrect());
    }

    @Test
    public void wrongAnswer() {
        Answer<List<Integer>> answer = new AnswerImpl<>(Arrays.asList(1, 3));
        assertFalse(question.isAnswerCorrect());
        question.giveAnswer(answer);
        assertFalse(question.isAnswerCorrect());
    }

    @Test
    public void immutableAnswer() {
        Answer<List<Integer>> answer = new AnswerImpl<>(Arrays.asList(1, 3));
        assertFalse(question.isAnswerCorrect());
        question.giveAnswer(answer);
        Answer<List<Integer>> newAnswer = new AnswerImpl<>(Arrays.asList(0, 1, 3));
        question.giveAnswer(newAnswer);
        assertFalse(question.isAnswerCorrect());
    }
}