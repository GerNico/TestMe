package com.test.bysiness.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
class PassedTest {
    Long passedTestId;
    Long testId;
    List<Answer> answers;
    LocalDateTime startDate;
    LocalDateTime lastChangeDate;
}
