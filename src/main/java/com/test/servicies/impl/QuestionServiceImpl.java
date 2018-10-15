package com.test.servicies.impl;

import com.test.bysiness.dto.QuestionData;
import com.test.bysiness.entities.QuestionEntity;
import com.test.bysiness.functions.QuestionTransformRules;
import com.test.repositories.QuestionRepository;
import com.test.servicies.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionEntity save(QuestionEntity questionToPersist) {
        return questionRepository.save(questionToPersist);
    }

    public QuestionData saveFromDTO(QuestionData question) {
        return QuestionTransformRules.questionEntityToQuestion.apply(
                questionRepository.save(
                        QuestionTransformRules.questionToQuestionEntity.apply(question)
                )
        );
    }

    public QuestionEntity get(Long id) {
        return questionRepository.findOne(id);
    }

    public QuestionData getDTO(Long id) {
        QuestionEntity questionEntity= questionRepository.findOne(id);
        return QuestionTransformRules.questionEntityToQuestion.apply(questionEntity);
    }

    public void delete(Long id) {
        questionRepository.delete(id);
    }
}
