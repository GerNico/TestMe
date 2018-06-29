package com.test.bysiness.dto;

import com.test.bysiness.entities.TestEntity;
import com.test.bysiness.entities.UserEntity;

import java.util.List;
import java.util.Set;

public class CourseDTO {
    private Long id;
    private String courseDescription;
    private List<TestEntity> tests;
    private Set<UserEntity> subscribedUsers;

    public CourseDTO() {
    }

    public CourseDTO(String courseDescription, List<TestEntity> tests, Set<UserEntity> subscribedUsers) {
        this.courseDescription = courseDescription;
        this.tests = tests;
        this.subscribedUsers = subscribedUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public List<TestEntity> getTests() {
        return tests;
    }

    public void setTests(List<TestEntity> tests) {
        this.tests = tests;
    }

    public Set<UserEntity> getSubscribedUsers() {
        return subscribedUsers;
    }

    public void setSubscribedUsers(Set<UserEntity> subscribedUsers) {
        this.subscribedUsers = subscribedUsers;
    }
}
