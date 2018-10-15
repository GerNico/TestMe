//package com.test.servicies;
//
//import com.test.bysiness.dto.*;
//import com.test.bysiness.entities.OptionEntity;
//import com.test.bysiness.entities.QuestionEntity;
//import com.test.bysiness.entities.UserEntity;
//import com.test.bysiness.functions.QuestionTransformRules;
//import com.test.bysiness.utilities.QuestionType;
//import com.test.bysiness.utilities.Roles;
//import com.test.servicies.impl.AnswerServiceImpl;
//import com.test.servicies.impl.QuestionServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.util.Collections;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Stream;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@WebAppConfiguration
//@RequiredArgsConstructor
//public class AnswerServiceImplTest {
//
//    private final QuestionServiceImpl questionServiceImpl;
//    private final AnswerServiceImpl answerServiceImpl;
//    private final UserService userService;
//    private final CourseService courseService;
//    private final TestService testService;
//    private QuestionEntity questionToPersist;
//    private CourseData newCourse;
//    private TestData newTest;
//
//
//    @Before
//    public void init() {
//        if (questionToPersist != null && questionToPersist.getId() != null) {
//            questionServiceImpl.delete(questionToPersist.getId());
//        }
//        questionToPersist = new QuestionEntity();
//
//        CourseData courseOfFantasy = new CourseData();
//        courseOfFantasy.setCourseDescription("just classic, no anime");
//        newCourse = courseService.createNewCourse(courseOfFantasy);
//
//        TestData testToPersist = new TestData();
//        testToPersist.setTestDescription("Tolkien heroes test");
//        testToPersist.setQuestions();
//        testService.saveFromDTO()
//
//    }
//
//    @After
//    public void cleanUp(){
//        testService.delete(newTest.getId());
//        courseService.delete(newCourse.getId());
//    }
//
//    @Test
//    public void userGivesAnswerOnQuestion() {
//        UserEntity user = setUpSomeUser();
//        user = userService.save(user);
//
//
//        userService.subscribeUserOnToCourse();
//
//
//        persistQuestionBauglir();
//        QuestionData question = QuestionTransformRules.questionEntityToQuestion.apply(questionToPersist);
//        AnswerData answer = new AnswerData();
//        Optional<OptionData> one_selected = answeringBauglirQuestion(question, answer);
//        assertTrue(one_selected.isPresent());
//        long optionNumber = one_selected.map(OptionData::getNumberInSequence).orElse(-1L);
//        long expectedOptionNumber = 3L;
//        assertEquals(expectedOptionNumber, optionNumber);
//        answer.setSelectedOptions(Collections.singletonList(optionNumber));
//        assertEquals(expectedOptionNumber, answer.getSelectedOptions().get(0).longValue());
//
//        Optional<AnswerData> persistedAnswer = answerServiceImpl.newAnswer(answer);
//        assertTrue(persistedAnswer.isPresent());
//        answerServiceImpl.deleteAnswerEntity(persistedAnswer.map(AnswerData::getAnswerId).orElse(-1L));
//
//        userService.delete(user.getId());
//    }
//
//    private UserEntity setUpSomeUser() {
//        UserEntity user = new UserEntity();
//        user.setLogin("userToDelete");
//        user.setEmail("userToDelete@email.com");
//        user.setPasswordHash("SVKANDV-savashifbna-kdmpkpokmkbd");
//        user.setRole(Roles.Admin);
//        return user;
//    }
//
//    private Optional<OptionData> answeringBauglirQuestion(QuestionData question, AnswerData answer) {
//        answer.setQuestionId(question.getId());
//        return question.getOptions().stream()
//                .filter(option -> option.getText()
//                        .startsWith("One among")).findAny();
//    }
//
//    private void persistQuestionBauglir() {
//        String FAILED_TO_PERSIST_QUESTION = "failed to persist question";
//        questionToPersist.setQuestion("Who was Morgoth Bauglir?");
//        questionToPersist.setType(QuestionType.WITH_OPTIONS);
//
//        OptionEntity option1 = new OptionEntity();
//        option1.setText("Lord of the rings");
//        option1.setNumberInSequence(0L);
//        OptionEntity option2 = new OptionEntity();
//        option2.setText("Proud elven king");
//        option2.setNumberInSequence(1L);
//        OptionEntity option3 = new OptionEntity();
//        option3.setText("Rogue and swindler");
//        option3.setNumberInSequence(2L);
//        OptionEntity option4 = new OptionEntity();
//        option4.setText("One among Midleearth creators");
//        option4.setNumberInSequence(3L);
//        option4.setCorrect(true);
//
//        Stream.of(option1, option2, option3, option4).forEach(questionToPersist::addOption);
//
//        questionToPersist = questionServiceImpl.save(questionToPersist);
//        assertNotNull(FAILED_TO_PERSIST_QUESTION, questionToPersist.getId());
//        assertNull(FAILED_TO_PERSIST_QUESTION, questionToPersist.getAnswerForNoOptions());
//        assertNotNull(FAILED_TO_PERSIST_QUESTION, questionToPersist.getQuestion());
//        Set<OptionEntity> persistedOptions = questionToPersist.getOptions();
//        long numberOfOptions = persistedOptions.size();
//        assertEquals(4, numberOfOptions);
//        long numberOfCorrectOptions = persistedOptions.stream().filter(OptionEntity::isCorrect).count();
//        assertEquals(1, numberOfCorrectOptions);
//        long numberOfParentQuestions = persistedOptions.stream().map(OptionEntity::getParentQuestion).distinct().count();
//        assertEquals(1, numberOfParentQuestions);
//    }
//
//
//}
