package com.test.rest;

import com.test.bysiness.dto.Question;
import com.test.bysiness.dto.Test;
import com.test.bysiness.entities.QuestionEntity;
import com.test.bysiness.entities.TestEntity;
import com.test.bysiness.functions.QuestionTransformRules;
import com.test.bysiness.functions.TestTransformRules;
import com.test.bysiness.utilities.Messages;
import com.test.servicies.impl.TestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.test.bysiness.utilities.Messages.QUESTION_SAVED;
import static com.test.bysiness.utilities.Messages.TEST_NOT_EXIST;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestServiceImpl testServiceImpl;

    @RequestMapping(value = "rest/test/{id}", method = RequestMethod.GET)
    public ResponseEntity<Test> findTestById(@PathVariable Long id) {
        TestEntity testEntity = testServiceImpl.get(id);
        if (testEntity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(TestTransformRules.testEntityToTest.apply(testEntity), HttpStatus.OK);
    }

    @RequestMapping(value = "rest/test/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Test> deleteTestById(@PathVariable Long id) {
        testServiceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "rest/test/{testId}/questions", method = RequestMethod.POST)
    public ResponseEntity<Test> insertQuestionInTest(@PathVariable Long testId, @RequestBody Question question) {
        TestEntity testEntity = testServiceImpl.get(testId);
        if (testEntity == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        testEntity.addQuestion(QuestionTransformRules.questionToQuestionEntity.apply(question));
        testEntity = testServiceImpl.save(testEntity);
        return new ResponseEntity<>(TestTransformRules.testEntityToTest.apply(testEntity), HttpStatus.OK);
    }

    @RequestMapping(value = "rest/test/{testId}/question/{questionId}", method = RequestMethod.POST)
    public ResponseEntity<Messages> editQuestionInTest(@PathVariable Long testId, @PathVariable Long questionId, @RequestBody Question question) {
        TestEntity testEntity = testServiceImpl.get(testId);
        if (testEntity == null) return new ResponseEntity<>(TEST_NOT_EXIST, HttpStatus.BAD_REQUEST);
        QuestionEntity newQuestionEntity = QuestionTransformRules.questionToQuestionEntity.apply(question);
        Optional<QuestionEntity> questionToUpdate = testEntity.getQuestions().stream()
                .filter(ent -> ent.getId().equals(questionId))
                .findFirst();
        questionToUpdate.ifPresent(q -> {
            q.setQuestion(newQuestionEntity.getQuestion());
            q.setType(newQuestionEntity.getType());
            q.setAnswerForNoOptions(newQuestionEntity.getAnswerForNoOptions());
        });
        testServiceImpl.save(testEntity);
        return new ResponseEntity<>(QUESTION_SAVED, HttpStatus.OK);
    }

}
