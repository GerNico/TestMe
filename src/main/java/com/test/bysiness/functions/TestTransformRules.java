package com.test.bysiness.functions;

import com.test.bysiness.dto.Test;
import com.test.bysiness.entities.TestEntity;

import java.util.function.Function;
import java.util.stream.Collectors;

public class TestTransformRules {
    public static Function<TestEntity, Test> testEntityToTest = testEntity -> {
        Test test = new Test();
        test.setId(testEntity.getId());
        test.setTestDescription(testEntity.getTestDescription());
        test.setQuestions(
                testEntity.getQuestions().stream()
                        .map(QuestionTransformRules.questionEntityToQuestion)
                        .collect(Collectors.toList()));
        return test;
    };

    public static Function<Test, TestEntity> testToTestEntity = test -> {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(test.getId());
        testEntity.setTestDescription(test.getTestDescription());
        test.getQuestions().stream()
                .map(QuestionTransformRules.questionToQuestionEntity)
                .forEach(testEntity::addQuestion);
        return testEntity;
    };
}
