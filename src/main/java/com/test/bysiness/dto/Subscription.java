package com.test.bysiness.dto;

import lombok.Data;

import java.util.List;

@Data
public class Subscription {
    Long subscriptionId;
    Long courseId;
    Long userId;
    List<PassedTest> passedTests;
}
