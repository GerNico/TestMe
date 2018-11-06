package com.test.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CourseUsageController {
//    private final TestServiceImpl testServiceImpl;
//
//    @RequestMapping(value = "rest/test/{id}", method = RequestMethod.GET)
//    public ResponseEntity<TestData> findTestById(@PathVariable Long id) {
//        TestEntity testEntity = testServiceImpl.get(id);
//        if (testEntity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(TestTransformRules.testEntityToTest.apply(testEntity), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "rest/test/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<TestData> deleteTestById(@PathVariable Long id) {
//        testServiceImpl.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "rest/test/{testId}/questions", method = RequestMethod.POST)
//    public ResponseEntity<TestData> insertQuestionInTest(@PathVariable Long testId, @RequestBody QuestionData question) {
//        TestEntity testEntity = testServiceImpl.get(testId);
//        if (testEntity == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        testEntity.addQuestion(QuestionTransformRules.questionToQuestionEntity.apply(question));
//        testEntity = testServiceImpl.save(testEntity);
//        return new ResponseEntity<>(TestTransformRules.testEntityToTest.apply(testEntity), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "rest/test/{testId}/question/{questionId}", method = RequestMethod.POST)
//    public ResponseEntity<Messages> editQuestionInTest(@PathVariable Long testId, @PathVariable Long questionId, @RequestBody QuestionData question) {
//        TestEntity testEntity = testServiceImpl.get(testId);
//        if (testEntity == null) return new ResponseEntity<>(TEST_NOT_EXIST, HttpStatus.BAD_REQUEST);
//        QuestionEntity newQuestionEntity = QuestionTransformRules.questionToQuestionEntity.apply(question);
//        Optional<QuestionEntity> questionToUpdate = testEntity.getQuestions().stream()
//                .filter(ent -> ent.getId().equals(questionId))
//                .findFirst();
//        questionToUpdate.ifPresent(q -> {
//            q.setQuestion(newQuestionEntity.getQuestion());
//            q.setType(newQuestionEntity.getType());
//            q.setAnswerForNoOptions(newQuestionEntity.getAnswerForNoOptions());
//        });
//        testServiceImpl.save(testEntity);
//        return new ResponseEntity<>(QUESTION_SAVED, HttpStatus.OK);
//    }
}
