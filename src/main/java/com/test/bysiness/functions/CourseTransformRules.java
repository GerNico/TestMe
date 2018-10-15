package com.test.bysiness.functions;

import com.test.bysiness.dto.CourseData;
import com.test.bysiness.dto.CourseInfo;
import com.test.bysiness.entities.CourseEntity;

import java.util.function.Function;
import java.util.stream.Collectors;

public class CourseTransformRules {

    public static Function<CourseEntity, CourseData> courseEntityToCourse = courseEntity -> {
        CourseData course = new CourseData();
        course.setId(courseEntity.getId());
        course.setCourseName(courseEntity.getCourseName());
        course.setCourseDescription(courseEntity.getCourseDescription());
        course.setPictureUrl(courseEntity.getCourseUrl());
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

    public static Function<CourseEntity, CourseInfo> courseEntityToCourseInfo = courseEntity -> {
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setId(courseEntity.getId());
        courseInfo.setName(courseEntity.getCourseName());
        courseInfo.setPictureLink(courseEntity.getCourseUrl());
        return courseInfo;
    };
}
