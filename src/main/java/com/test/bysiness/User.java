package com.test.bysiness;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "login",unique = true,nullable = false)
    private String login;
    @Column(name = "email",unique = true,nullable = false)
    private String email;
    @Column(name = "user_password",nullable = false)
    private String passwordHash;
    @Transient
    private List<Course> courses;

    public User() {
    }

    public User(Integer id, String login, String email, String passwordHash, List<Course> courses) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.passwordHash = passwordHash;
        this.courses = courses;
    }

    public User(String login, String email, String passwordHash, List<Course> courses) {
        this.login = login;
        this.email = email;
        this.passwordHash = passwordHash;
        this.courses = courses;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) &&
                Objects.equals(passwordHash, user.passwordHash) &&
                Objects.equals(courses, user.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, email, passwordHash, courses);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", courses=" + courses +
                '}';
    }
}
