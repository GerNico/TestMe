package com.test.bysiness.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class Course {
    private long id;
    private String courseName;
    private String courseDescription;
    private Set<Test> tests = new HashSet<>();
    private Set<Subscriber> subscribedUsers = new HashSet<>();
}
