package com.test.bysiness.questions;

import com.test.bysiness.Answer;
import com.test.bysiness.Question;
import com.test.bysiness.Test;
import com.test.bysiness.Topic;
import com.test.bysiness.answers.AnswerImpl;
import com.test.bysiness.courses.TestImpl;
import org.junit.Before;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestTest {
    Test test;

    @Before
    public void init() {
        String theQuestion1 = "Are you gay?";
        Topic theTopic1 = new Topic(3, "Sexual");
        List<String> options1 = Arrays.asList("Yes", "No");
        Integer score1 = 2;
        Answer<Integer> correctAnswer1 = new AnswerImpl<>(1);
        Question<Integer> question1 = new QuestionWithOptions<>(theQuestion1, options1, theTopic1, correctAnswer1, score1);

        String theQuestion2 = "select the odd numbers";
        Topic theTopic2 = new Topic(1, "Arithmetic");
        List<String> options2 = Arrays.asList("0", "2", "5", "8");
        Integer score2 = 1;
        Answer<List<Integer>> correctAnswer2 = new AnswerImpl<>(Arrays.asList(0, 1, 3));
        Question<List<Integer>> question2 = new QuestionWithOptions<>(theQuestion2, options2, theTopic2, correctAnswer2, score2);

        String theQuestion3 = "Who was lord of the ring?";
        Topic theTopic3 = new Topic(2, "Cinema");
        Answer<String> correctAnswer3 = new AnswerImpl<>("Sauron");
        Integer score3 = 3;
        Question<String> question3 = new QuestionWithoutOptions<>(theQuestion3, theTopic3, correctAnswer3, score3);

        test = new TestImpl(Arrays.asList(question1, question2, question3), 4);
    }

    @org.junit.Test
    public void testCheckUp() {
        Answer<Integer> answer1= new AnswerImpl<>(1);
        Answer<List<Integer>> answer2= new AnswerImpl<>(Arrays.asList(0, 1, 0));
        Answer<String> answer3 = new AnswerImpl<>("Frodo");

        test.getAllQuestions().get(0).giveAnswer(answer1);
        test.getAllQuestions().get(1).giveAnswer(answer2);
        test.getAllQuestions().get(2).giveAnswer(answer3);

    }
}
