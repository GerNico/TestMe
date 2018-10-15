package com.test.servicies;

import com.test.bysiness.dto.Question;
import com.test.bysiness.entities.QuestionEntity;

public interface QuestionService {
    QuestionEntity save(QuestionEntity questionToPersist);

    Question saveFromDTO(Question question);

    QuestionEntity get(Long id);

    Question getDTO(Long id);

    void delete(Long id);
}
