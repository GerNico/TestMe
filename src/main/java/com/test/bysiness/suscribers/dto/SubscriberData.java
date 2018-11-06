package com.test.bysiness.suscribers.dto;

import com.test.utilities.Roles;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class SubscriberData {
    private long id;
    private String login;
    private String email;
    private List<Roles> roles;
    private Set<Long> myCoursesIds;
    private Set<Long> subscribedCourseIds;
}