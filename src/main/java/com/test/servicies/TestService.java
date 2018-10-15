package com.test.servicies;

import com.test.bysiness.dto.QuestionData;
import com.test.bysiness.dto.TestData;
import com.test.bysiness.entities.TestEntity;

public interface TestService {
    TestEntity save(TestEntity test);

    TestData saveFromDTO(TestData test);

    TestEntity get(Long id);

    TestData addQuestionToTest(Long testId, QuestionData question);

    TestData getDTO(Long id);

    void delete(Long id);
}
