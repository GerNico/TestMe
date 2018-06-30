package com.test.bysiness.functions;

import com.test.bysiness.dto.Test;
import com.test.bysiness.entities.TestEntity;

import java.util.function.Function;
import java.util.stream.Collectors;

public class TestEntityToTest implements Function<TestEntity, Test> {
    @Override
    public Test apply(TestEntity testEntity) {
        return convert(testEntity);
    }

    public static Test convert(TestEntity testEntity) {
        Test test = new Test();
        test.setId(testEntity.getId());
        test.setTestDescription(testEntity.getTestDescription());
        test.setQuestions(
                testEntity.getQuestions().stream()
                        .map(QuestionEntityToQuestion::convert)
                        .collect(Collectors.toList()));
        return test;
    }
}
