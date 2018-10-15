package com.test.servicies.impl;

import com.test.bysiness.dto.AnswerData;
import com.test.bysiness.entities.AnswerEntity;
import com.test.bysiness.entities.PassedTestEntity;
import com.test.bysiness.entities.QuestionEntity;
import com.test.bysiness.functions.QuestionTransformRules;
import com.test.repositories.AnswerRepository;
import com.test.repositories.PassedTestRepository;
import com.test.repositories.QuestionRepository;
import com.test.servicies.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final PassedTestRepository passedTestRepository;

    public Optional<AnswerData> newAnswer(AnswerData answerData) {
        QuestionEntity questionEntity = questionRepository.findOne(answerData.getQuestionId());
        PassedTestEntity passedTestEntity = passedTestRepository.findOne(answerData.getPassedTestId());
        if (questionEntity == null) return Optional.empty();
        if (passedTestEntity == null) return Optional.empty();
        AnswerEntity answerEntity = QuestionTransformRules.answerToEmptyAnswerEntity.apply(answerData);
        answerEntity.setQuestionToAnswer(questionEntity);
        answerEntity.setParentPassedTest(passedTestEntity);
        answerEntity = answerRepository.save(answerEntity);
        return Optional.ofNullable(QuestionTransformRules.answerEntityToAnswer.apply(answerEntity));
    }

    public AnswerEntity findAnswer(Long answerId) {
        return answerRepository.findOne(answerId);
    }

    public Optional<AnswerData> updateAnswerEntity(AnswerData answerData) {
        AnswerEntity answerEntity = answerRepository.findOne(answerData.getAnswerId());
        QuestionEntity questionEntity = questionRepository.findOne(answerData.getQuestionId());
        PassedTestEntity passedTestEntity = passedTestRepository.findOne(answerData.getPassedTestId());
        if (questionEntity == null) return Optional.empty();
        if (passedTestEntity == null) return Optional.empty();
        if (answerEntity == null) return Optional.empty();
        answerEntity.setQuestionToAnswer(questionEntity);
        answerEntity.setParentPassedTest(passedTestEntity);
        answerEntity = answerRepository.save(answerEntity);
        return Optional.ofNullable(QuestionTransformRules.answerEntityToAnswer.apply(answerEntity));
    }

    public boolean deleteAnswerEntity(Long answerId) {
        AnswerEntity answerEntity = answerRepository.findOne(answerId);
        if (answerEntity == null) return false;
        answerRepository.delete(answerId);
        return true;
    }
}
