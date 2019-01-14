package com.test.servicies.impl;

import com.test.bysiness.usage.dto.AnswerData;
import com.test.repositories.CourseRepository;
import com.test.repositories.QuestionRepository;
import com.test.repositories.TestRepository;
import com.test.servicies.CourseUsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class CourseUsageServiceImpl implements CourseUsageService {
    private final CourseRepository courseRepository;
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
//Todo
    @Override
    public Optional<AnswerData> newAnswer(AnswerData answerData) {
//        QuestionEntity question = questionRepository.findOne(answerData.getQuestionId());
//        AnswerData answer = new AnswerData();
//        answer.
        return Optional.empty();
    }
}
