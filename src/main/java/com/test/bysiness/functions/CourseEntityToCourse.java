package com.test.bysiness.functions;

import com.test.bysiness.dto.Course;
import com.test.bysiness.entities.CourseEntity;

import java.util.function.Function;
import java.util.stream.Collectors;

public class CourseEntityToCourse implements Function<CourseEntity, Course> {
    @Override
    public Course apply(CourseEntity courseEntity) {
        return convert(courseEntity);
    }

    public static Course convert(CourseEntity courseEntity) {
        Course course = new Course();
        course.setId(courseEntity.getId());
        course.setCourseName(courseEntity.getCourseName());
        course.setCourseDescription(courseEntity.getCourseDescription());
        course.setTests(
                courseEntity.getTests().stream()
                        .map(TestEntityToTest::convert)
                        .collect(Collectors.toSet()));
        course.setSubscribedUsers(
                courseEntity.getSubscribedUsers().stream()
                        .map(UserEntityToSubscriber::convert)
                        .collect(Collectors.toSet()));
        return course;
    }
}
