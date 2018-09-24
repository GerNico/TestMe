package com.test.bysiness.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Course {
    private long id;
    private String courseName;
    private String pictureUrl;
    private String courseDescription;
    private Set<Test> tests = new HashSet<>();
    private Set<Subscriber> subscribedUsers = new HashSet<>();
}
