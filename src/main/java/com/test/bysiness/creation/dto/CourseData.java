package com.test.bysiness.creation.dto;

import com.test.bysiness.suscribers.dto.SubscriberData;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CourseData {
    private long id;
    private String courseName;
    private String pictureUrl;
    private String courseDescription;
    private Set<TestData> tests = new HashSet<>();
    private Set<SubscriberData> subscribedUsers = new HashSet<>();
}
