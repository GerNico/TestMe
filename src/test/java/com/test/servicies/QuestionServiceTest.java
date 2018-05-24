package com.test.servicies;

import com.test.bysiness.entities.OptionEntity;
import com.test.bysiness.entities.QuestionEntity;
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
    private QuestionEntity questionToPersist;
    @Autowired
    private QuestionService questionService;

    @Before
    public void init() {
        questionToPersist = new QuestionEntity();
    }

    @Test
    public void persistQuestion() {
        questionToPersist.setQuestion("Who was Morgoth Bauglir?");
        questionToPersist.setType("with_options");

        OptionEntity option1 = new OptionEntity();
        option1.setText("Lord of the rings");
        OptionEntity option2 = new OptionEntity();
        option2.setText("Proud elven king");
        OptionEntity option3 = new OptionEntity();
        option3.setText("Rogue and swindler");
        OptionEntity option4 = new OptionEntity();
        option4.setText("One among Midleearth creators");
        option4.setIsCorrect(true);

        Stream.of(option1, option2, option3, option4).forEach(questionToPersist::addOption);

        questionToPersist = questionService.save(questionToPersist);
        assertNotNull(questionToPersist.getId());
        assertNull(questionToPersist.getAnswerForNoOptions());
        assertNotNull(questionToPersist.getQuestion());
        Set<OptionEntity> persistedOptions = questionToPersist.getOptions();
        long numberOfOptions = persistedOptions.size();
        assertEquals(4, numberOfOptions);
        long numberOfCorrectOptions = persistedOptions.stream().filter(OptionEntity::getIsCorrect).count();
        assertEquals(1, numberOfCorrectOptions);
        long numberOfParentQuestions = persistedOptions.stream().map(OptionEntity::getParentQuestion).distinct().count();
        assertEquals(1,numberOfParentQuestions);
        questionService.delete(questionToPersist.getId());
    }
}
