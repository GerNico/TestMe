//package com.test.servicies;
//
//import com.test.bysiness.dto.Answer;
//import com.test.bysiness.dto.Option;
//import com.test.bysiness.dto.Question;
//import com.test.bysiness.entities.OptionEntity;
//import com.test.bysiness.entities.QuestionEntity;
//import com.test.bysiness.functions.QuestionTransformRules;
//import com.test.bysiness.utilities.QuestionType;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
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
//public class AnswerServiceImplTest {
//    private QuestionEntity questionToPersist;
//
//    @Autowired
//    private QuestionServiceImpl questionServiceImpl;
//    @Autowired
//    private AnswerServiceImpl answerServiceImpl;
//
//    @Before
//    public void init() {
//        if (questionToPersist != null && questionToPersist.getId() != null) {
//            questionServiceImpl.delete(questionToPersist.getId());
//        }
//        questionToPersist = new QuestionEntity();
//    }
//
//    @Test
//    public void userGivesAnswerOnQuestion() {
//        persistQuestionBauglir();
//        Question question = QuestionTransformRules.questionEntityToQuestion.apply(questionToPersist);
//        Answer answer = new Answer();
//        answer.setQuestionId(question.getId());
//        Optional<Option> one_selected = question.getOptions().stream()
//                .filter(option -> option.getText()
//                        .startsWith("One among")).findAny();
//        assertTrue(one_selected.isPresent());
//        long optionNumber = one_selected.map(Option::getNumberInSequence).orElse(-1L);
//        long expectedOptionNumber = 3L;
//        assertEquals(expectedOptionNumber, optionNumber);
//        answer.setSelectedOptions(Collections.singletonList(optionNumber));
//        assertEquals(expectedOptionNumber, answer.getSelectedOptions().get(0).longValue());
//        Optional<Answer> persistedAnswer = answerServiceImpl.newAnswer(answer);
//        assertTrue(persistedAnswer.isPresent());
//        answerServiceImpl.deleteAnswerEntity(persistedAnswer.map(Answer::getAnswerId).orElse(-1L));
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
//        option4.setIsCorrect(true);
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
//        long numberOfCorrectOptions = persistedOptions.stream().filter(OptionEntity::getIsCorrect).count();
//        assertEquals(1, numberOfCorrectOptions);
//        long numberOfParentQuestions = persistedOptions.stream().map(OptionEntity::getParentQuestion).distinct().count();
//        assertEquals(1, numberOfParentQuestions);
//    }
//
//
//}
