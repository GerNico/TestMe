package com.test.bysiness.usage.dto;

import com.test.utilities.PassingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PassedTestData {
    private Long id;
    private LocalDateTime passingDate;
    private String lastChangeDate;
    private PassingStatus passingStatus;
    private Long score;
    private List<AnswerData> ansers;
}
