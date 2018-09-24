package com.test.servicies;

import com.test.bysiness.entities.CourseEntity;
import com.test.bysiness.entities.OptionEntity;
import com.test.bysiness.entities.QuestionEntity;
import com.test.bysiness.entities.TestEntity;
import com.test.bysiness.utilities.QuestionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CourceModificationTest {
    @Autowired
    private CourseService courseService;

    @Test
    public void persistingTest() {

        CourseEntity courseOfFantasy=new CourseEntity();
        courseOfFantasy.setCourseDescription("just classic, no anime");

        TestEntity testToPersist = new TestEntity();
        testToPersist.setTestDescription("Tolkien heroes test");
        testToPersist.setParentCourse(courseOfFantasy);

        QuestionEntity questionToPersist = new QuestionEntity();
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
        option4.setIsCorrect(true);

        Stream.of(option1, option2, option3, option4).forEach(questionToPersist::addOption);

        QuestionEntity questionToPersist2 = new QuestionEntity();

        questionToPersist2.setQuestion("Name of mister Baggins, from brotherhood of the ring?");
        questionToPersist2.setType(QuestionType.WITHOUT_OPTIONS);
        questionToPersist2.setAnswerForNoOptions("Frodo");

        Stream.of(questionToPersist, questionToPersist2).forEach(testToPersist::addQuestion);
        courseOfFantasy.addTest(testToPersist);
        courseOfFantasy = courseService.save(courseOfFantasy);
        courseService.delete(courseOfFantasy.getId());
    }
}
