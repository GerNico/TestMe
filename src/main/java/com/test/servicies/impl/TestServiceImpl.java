package com.test.servicies.impl;

import com.test.bysiness.dto.QuestionData;
import com.test.bysiness.dto.TestData;
import com.test.bysiness.entities.TestEntity;
import com.test.bysiness.functions.QuestionTransformRules;
import com.test.bysiness.functions.TestTransformRules;
import com.test.repositories.TestRepository;
import com.test.servicies.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    public TestEntity save(TestEntity test) {
        return testRepository.save(test);
    }

    public TestData saveFromDTO(TestData test) {
        return TestTransformRules.testEntityToTest.apply(
                testRepository.save(TestTransformRules.testToTestEntity.apply(test)));
    }

    public TestEntity get(Long id) {
        return testRepository.findOne(id);
    }

    public TestData addQuestionToTest(Long testId, QuestionData question) {
        TestEntity testEntity = testRepository.findOne(testId);
        testEntity.addQuestion(QuestionTransformRules.questionToQuestionEntity.apply(question));
        testRepository.save(testEntity);
        return TestTransformRules.testEntityToTest.apply(testEntity);
    }

    public TestData getDTO(Long id) {
        return TestTransformRules.testEntityToTest.apply(testRepository.findOne(id));
    }

    public void delete(Long id) {
        testRepository.delete(id);
    }
}
