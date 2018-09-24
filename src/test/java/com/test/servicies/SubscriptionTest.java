package com.test.servicies;

import com.test.bysiness.entities.*;
import com.test.bysiness.utilities.QuestionType;
import com.test.bysiness.utilities.Roles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SubscriptionTest {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    private static UserEntity userToSubscribe = new UserEntity();
    private static UserEntity userToUnSubscribe = new UserEntity();

    @Test
    public void subscribeUser() {
        CourseEntity courseWithSubscribers = courseTolkien();


        userToSubscribe.setEmail("first_user@some.com");
        userToSubscribe.setLogin("first_user");
        userToSubscribe.setPasswordHash("vkjsdhvgkjhsd-vfasmlvkj-1521");
        userToSubscribe.setRole(Roles.User);

        userToUnSubscribe.setEmail("second_user@some.com");
        userToUnSubscribe.setLogin("second_user");
        userToUnSubscribe.setPasswordHash("hasggafkj-mvklcml-1567");
        userToUnSubscribe.setRole(Roles.Admin);
        userToSubscribe = userService.save(userToSubscribe);
        userToUnSubscribe = userService.save(userToUnSubscribe);

        userToSubscribe.subscribe(courseWithSubscribers);

        userToUnSubscribe.subscribe(courseWithSubscribers);

        courseWithSubscribers = courseService.save(courseWithSubscribers);

        assertNotNull(userToSubscribe.getId());
        assertNotNull(userToUnSubscribe.getId());
        assertNotNull(courseWithSubscribers.getId());
        userService.delete(userToSubscribe.getId());
        userService.delete(userToUnSubscribe.getId());
        courseService.delete(courseWithSubscribers.getId());
    }

    private CourseEntity courseTolkien() {
        CourseEntity courseOfFantasy = new CourseEntity();
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
        questionToPersist2.setType(QuestionType.WITH_OPTIONS);
        questionToPersist2.setAnswerForNoOptions("Frodo");

        Stream.of(questionToPersist, questionToPersist2).forEach(testToPersist::addQuestion);
        courseOfFantasy.addTest(testToPersist);
        return courseOfFantasy;
    }
}
