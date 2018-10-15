package com.test.servicies;

import com.test.bysiness.dto.QuestionData;
import com.test.bysiness.entities.QuestionEntity;

public interface QuestionService {
    QuestionEntity save(QuestionEntity questionToPersist);

    QuestionData saveFromDTO(QuestionData question);

    QuestionEntity get(Long id);

    QuestionData getDTO(Long id);

    void delete(Long id);
}
