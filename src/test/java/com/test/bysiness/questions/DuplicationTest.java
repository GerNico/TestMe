package com.test.bysiness.questions;

import com.test.bysiness.Answer;
import com.test.bysiness.Question;
import com.test.bysiness.Topic;
import com.test.bysiness.answers.AnswerImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DuplicationTest {

    @Test
    public void duplicateDateAnswer() {
        Date date = new Date();
        Answer<Object> answer = new AnswerImpl<>(date);
        Answer<Object> answer2 = answer.duplicate();
        assertFalse(answer.get() == answer2.get());
        assertTrue(answer.get().equals(answer2.get()));
    }

    @Test
    public void duplicateQuestion(){
        String theQuestion = "Are you gay?";
        Topic theTopic = new Topic(3, "Sexual");
        List<String> options = Arrays.asList("Yes", "No");
        Integer score = 2;
        Answer<Integer> correctAnswer = new AnswerImpl<>(1);
        Question<Integer> question = new QuestionWithOptions<>(theQuestion, options, theTopic, correctAnswer, score);
        Question<Integer> question2=question.duplicate();
        question.giveAnswer(new AnswerImpl<>(1));
        assertFalse(question2.isAnswerCorrect());
        assertTrue(question.isAnswerCorrect());
        assertEquals(question.getOptions(),question2.getOptions());
        assertFalse(question.getOptions()==question2.getOptions());
    }


}
