package com.test.servicies;

import com.test.bysiness.dto.Question;
import com.test.bysiness.dto.Test;
import com.test.bysiness.entities.TestEntity;

public interface TestService {
    TestEntity save(TestEntity test);

    Test saveFromDTO(Test test);

    TestEntity get(Long id);

    Test addQuestionToTest(Long testId, Question question);

    Test getDTO(Long id);

    void delete(Long id);
}
