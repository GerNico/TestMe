package com.test.servicies;

import com.test.bysiness.questions.OptionFromDB;
import com.test.bysiness.questions.QuestionFromDB;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class QuestionServiceTest {
    private QuestionFromDB questionToPersist;
    @Autowired
    private QuestionService questionService;

    @Before
    public void init() {
        questionToPersist = new QuestionFromDB();
    }

    @Test
    public void persistQuestion() {
        questionToPersist.setQuestion("Who was Morgoth Bauglir?");
        questionToPersist.setType("with_options");

        OptionFromDB option1 = new OptionFromDB();
        option1.setText("Lord of the rings");
        OptionFromDB option2 = new OptionFromDB();
        option2.setText("Proud elven king");
        OptionFromDB option3 = new OptionFromDB();
        option3.setText("Rogue and swindler");
        OptionFromDB option4 = new OptionFromDB();
        option4.setText("One among world creators");
        option4.setIsCorrect(true);

        Stream.of(option1, option2, option3, option4).forEach(questionToPersist::addOption);

        questionToPersist = questionService.save(questionToPersist);
        assertNotNull(questionToPersist.getId());
        assertNull(questionToPersist.getAnswerForNoOptions());
        assertNotNull(questionToPersist.getQuestion());
        Set<OptionFromDB> persistedOptions = questionToPersist.getOptions();
    }
}
