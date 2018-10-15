package com.test.servicies;

import com.test.bysiness.dto.AnswerData;
import com.test.bysiness.entities.AnswerEntity;

import java.util.Optional;

public interface AnswerService {

    Optional<AnswerData> newAnswer(AnswerData answerData);

    AnswerEntity findAnswer(Long answerId);

    Optional<AnswerData> updateAnswerEntity(AnswerData answerData);

    boolean deleteAnswerEntity(Long answerId);
}
