package com.test.servicies;

import com.test.bysiness.dto.CourseData;
import com.test.bysiness.dto.CourseInfo;
import com.test.bysiness.entities.CourseEntity;
import com.test.bysiness.entities.UserEntity;

import java.util.stream.Stream;

public interface CourseService {

    CourseEntity save(CourseEntity course);

    CourseData createNewCourse(CourseData course);

    CourseData getCourseDTO(Long id);

    void delete(Long id);

    Stream<CourseInfo> getAllCoursesInfo();

    CourseEntity get(Long id);

    boolean unsuscribe(UserEntity user);
}
