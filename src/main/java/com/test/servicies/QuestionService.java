package com.test.servicies;

import com.test.bysiness.questions.QuestionFromDB;
import com.test.repositories.QuestionRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;


    public QuestionFromDB save(QuestionFromDB questionToPersist) {
        return questionRepository.save(questionToPersist);
    }
}
