package com.test.bysiness;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Course implements Serializable {
    private Integer id;
    private String description;
    private List<Test> tests;

    public Course() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) &&
                Objects.equals(description, course.description) &&
                Objects.equals(tests, course.tests);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, description, tests);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", tests=" + tests +
                '}';
    }
}
