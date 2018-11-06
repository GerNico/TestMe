package com.test.servicies;

import com.test.bysiness.usage.dto.AnswerData;

import java.util.Optional;

public interface CourseUsageService {

    Optional<AnswerData> newAnswer(AnswerData answerData);

}
