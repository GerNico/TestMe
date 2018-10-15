package com.test.bysiness.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public
class PassedTest {
    Long passedTestId;
    Long testId;
    List<AnswerData> answerData;
    LocalDateTime startDate;
    LocalDateTime lastChangeDate;
}
