package com.test.servicies;

import com.test.bysiness.creation.dto.CourseData;

import java.util.Optional;

public interface CourseCreationService {

    Optional<CourseData> createNewCourse(CourseData course);

}
