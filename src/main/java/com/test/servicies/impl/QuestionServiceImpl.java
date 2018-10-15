package com.test.servicies.impl;

import com.test.bysiness.dto.Question;
import com.test.bysiness.entities.QuestionEntity;
import com.test.bysiness.functions.QuestionTransformRules;
import com.test.repositories.QuestionRepository;
import com.test.servicies.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionEntity save(QuestionEntity questionToPersist) {
        return questionRepository.save(questionToPersist);
    }

    public Question saveFromDTO(Question question) {
        return QuestionTransformRules.questionEntityToQuestion.apply(
                questionRepository.save(
                        QuestionTransformRules.questionToQuestionEntity.apply(question)
                )
        );
    }

    public QuestionEntity get(Long id) {
        return questionRepository.findOne(id);
    }

    public Question getDTO(Long id) {
        QuestionEntity questionEntity= questionRepository.findOne(id);
        return QuestionTransformRules.questionEntityToQuestion.apply(questionEntity);
    }

    public void delete(Long id) {
        questionRepository.delete(id);
    }
}
