package com.test.servicies;

import com.test.bysiness.dto.Question;
import com.test.bysiness.entities.QuestionEntity;
import com.test.bysiness.functions.QuestionTransformRules;
import com.test.repositories.QuestionRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;


    QuestionEntity save(QuestionEntity questionToPersist) {
        return questionRepository.save(questionToPersist);
    }

    Question saveFromDTO(Question question) {
        return QuestionTransformRules.questionEntityToQuestion.apply(
                questionRepository.save(
                        QuestionTransformRules.questionToQuestionEntity.apply(question)
                )
        );
    }

    QuestionEntity get(Long id) {
        return questionRepository.findOne(id);
    }

    Question getDTO(Long id) {
        QuestionEntity questionEntity= questionRepository.findOne(id);
        return QuestionTransformRules.questionEntityToQuestion.apply(questionEntity);
    }

    void delete(Long id) {
        questionRepository.delete(id);
    }
}
