package com.test.bysiness.dto;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    private long id;
    private String login;
    private String email;
    private String role;
    private String passwordHash;
    private Set<CourseDTO> subscribedCourses = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Set<CourseDTO> getSubscribedCourses() {
        return subscribedCourses;
    }

    public void setSubscribedCourses(Set<CourseDTO> subscribedCourses) {
        this.subscribedCourses = subscribedCourses;
    }

    public UserDTO() {
    }

    public UserDTO(String login, String email, String role, String passwordHash, Set<CourseDTO> subscribedCourses) {
        this.login = login;
        this.email = email;
        this.role = role;
        this.passwordHash = passwordHash;
        this.subscribedCourses = subscribedCourses;
    }
}
