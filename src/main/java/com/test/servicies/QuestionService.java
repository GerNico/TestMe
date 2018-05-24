package com.test.servicies;

import com.test.bysiness.entities.QuestionEntity;
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

    void delete(Long id) {
        questionRepository.delete(id);
    }
}
