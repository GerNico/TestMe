package com.test.bysiness.dto;

import com.test.bysiness.entities.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class CourseProgressData {
    private Long courseProgressId;
    private CourseData course;
    private UserEntity suscriber;
    private List<PassedTest> passedTests;
}
