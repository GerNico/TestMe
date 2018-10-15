package com.test.servicies;

import com.test.bysiness.dto.Answer;
import com.test.bysiness.entities.AnswerEntity;

import java.util.Optional;

public interface AnswerService {

    Optional<Answer> newAnswer(Answer answer);

    AnswerEntity findAnswer(Long answerId);

    Optional<Answer> updateAnswerEntity(Answer answer);

    boolean deleteAnswerEntity(Long answerId);
}
