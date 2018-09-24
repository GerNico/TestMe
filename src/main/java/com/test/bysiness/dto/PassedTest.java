package com.test.bysiness.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PassedTest {
    Long passedTestEntity;
    Long testId;
    List<Answer> answers;
    LocalDateTime startDate;
    LocalDateTime lastChangeDate;
}
