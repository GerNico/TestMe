package com.test.bysiness.usage.dto;

import com.test.bysiness.creation.dto.CourseData;
import com.test.bysiness.suscribers.entities.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class CourseProgressData {
    private Long courseProgressId;
    private CourseData course;
    private UserEntity suscriber;
    private List<PassedTestData> passedTests;
}
