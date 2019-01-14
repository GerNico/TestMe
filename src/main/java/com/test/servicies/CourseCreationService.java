package com.test.servicies;

import com.test.bysiness.creation.dto.CourseData;
import com.test.bysiness.creation.dto.QuestionData;
import com.test.bysiness.creation.dto.TestData;

import java.util.Optional;

public interface CourseCreationService {

    Optional<CourseData> createNewCourse(CourseData course);

    Optional<TestData> createNewTest(TestData test,Long parentCourseId);

    Optional<QuestionData> createNewQuestion(QuestionData question,Long parentTestId);
}
