package com.test.servicies;

import com.test.bysiness.entities.OptionEntity;
import com.test.bysiness.entities.QuestionEntity;
import com.test.bysiness.utilities.QuestionType;
import com.test.servicies.impl.QuestionServiceImpl;
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
public class QuestionServiceImplTest {
    private QuestionEntity questionToPersist;
    @Autowired
    private QuestionServiceImpl questionServiceImpl;

    @Before
    public void init() {
        questionToPersist = new QuestionEntity();
    }

    @Test
    public void persistQuestion() {
        questionToPersist.setQuestion("Who was Morgoth Bauglir?");
        questionToPersist.setType(QuestionType.WITH_OPTIONS);

        OptionEntity option1 = new OptionEntity();
        option1.setText("Lord of the rings");
        OptionEntity option2 = new OptionEntity();
        option2.setText("Proud elven king");
        OptionEntity option3 = new OptionEntity();
        option3.setText("Rogue and swindler");
        OptionEntity option4 = new OptionEntity();
        option4.setText("One among Midleearth creators");
        option4.setCorrect(true);

        Stream.of(option1, option2, option3, option4).forEach(questionToPersist::addOption);

        questionToPersist = questionServiceImpl.save(questionToPersist);
        assertNotNull(questionToPersist.getId());
        assertNull(questionToPersist.getAnswerForNoOptions());
        assertNotNull(questionToPersist.getQuestion());
        Set<OptionEntity> persistedOptions = questionToPersist.getOptions();
        long numberOfOptions = persistedOptions.size();
        assertEquals(4, numberOfOptions);
        long numberOfCorrectOptions = persistedOptions.stream().filter(OptionEntity::isCorrect).count();
        assertEquals(1, numberOfCorrectOptions);
        long numberOfParentQuestions = persistedOptions.stream().map(OptionEntity::getParentQuestion).distinct().count();
        assertEquals(1,numberOfParentQuestions);
        questionServiceImpl.delete(questionToPersist.getId());
    }
}
