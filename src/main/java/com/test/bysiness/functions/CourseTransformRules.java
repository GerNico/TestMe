package com.test.bysiness.functions;

import com.test.bysiness.dto.Course;
import com.test.bysiness.entities.CourseEntity;

import java.util.function.Function;
import java.util.stream.Collectors;

public class CourseTransformRules {

    public static Function<CourseEntity, Course> optionEntityToOption = courseEntity -> {
        Course course = new Course();
        course.setId(courseEntity.getId());
        course.setCourseName(courseEntity.getCourseName());
        course.setCourseDescription(courseEntity.getCourseDescription());
        course.setTests(
                courseEntity.getTests().stream()
                        .map(TestTransformRules.testEntityToTest)
                        .collect(Collectors.toSet()));
        course.setSubscribedUsers(
                courseEntity.getSubscribedUsers().stream()
                        .map(UserTransformRules.userEntityToSubscriber)
                        .collect(Collectors.toSet()));
        return course;
    };
}
