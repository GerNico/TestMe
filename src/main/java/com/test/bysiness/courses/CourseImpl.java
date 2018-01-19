package com.test.bysiness.courses;

import java.util.List;

public class CourseImpl implements Course {
    private List<Test> tests;
    private int progress;
    private int maxScore;

    public CourseImpl(List<Test> tests) {
        this.tests = tests;
        this.progress =0;
    }

    @Override
    public List<String> getAuthors() {
        return null;
    }

    @Override
    public List<Test> getAllTests() {
        return null;
    }

    @Override
    public Test getTestById(Integer id) {
        return null;
    }

    @Override
    public Test getTestByName(String name) {
        return null;
    }
}
