package com.test.rest;

import com.test.bysiness.dto.Course;
import com.test.bysiness.dto.CourseInfo;
import com.test.bysiness.dto.Question;
import com.test.bysiness.dto.Test;
import com.test.bysiness.entities.CourseEntity;
import com.test.bysiness.entities.TestEntity;
import com.test.bysiness.functions.QuestionTransformRules;
import com.test.bysiness.functions.TestTransformRules;
import com.test.bysiness.utilities.Messages;
import com.test.servicies.impl.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.test.bysiness.utilities.Messages.COURSE_DOES_NOT_EXIST;
import static com.test.bysiness.utilities.Messages.TEST_ADDED;
import static com.test.bysiness.utilities.Messages.TEST_NOT_EXIST;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseServiceImpl courseServiceImpl;

    @RequestMapping(value = "rest/courses", method = RequestMethod.POST)
    public ResponseEntity<Course> createCourse(@RequestBody Course courseFromRequest) {
        Course course = courseServiceImpl.createNewCourse(courseFromRequest);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @RequestMapping(value = "rest/courses", method = RequestMethod.GET)
    public ResponseEntity<List<CourseInfo>> getInfoAboutAllCourses() {
        return new ResponseEntity<>(courseServiceImpl.getAllCoursesInfo().collect(Collectors.toList()), HttpStatus.OK);
    }

    @RequestMapping(value = "rest/course/{id}", method = RequestMethod.GET)
    public ResponseEntity<Course> findCourse(@PathVariable Long id) {
        Course course = courseServiceImpl.getCourseDTO(id);
        if (course == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @RequestMapping(value = "rest/course/{id}/tests", method = RequestMethod.POST)
    public ResponseEntity<Messages> createTestForCourse(@PathVariable Long id, @RequestBody Test test) {
        CourseEntity courseEntity = courseServiceImpl.get(id);
        if (courseEntity == null) return new ResponseEntity<>(Messages.COURSE_DOES_NOT_EXIST,HttpStatus.NOT_FOUND);
        courseEntity.addTest(TestTransformRules.testToTestEntity.apply(test));
        courseServiceImpl.save(courseEntity);
        return new ResponseEntity<>(TEST_ADDED,HttpStatus.OK);
    }

    @RequestMapping(value = "rest/course/{courseId}/test/{testId}/questions", method = RequestMethod.POST)
    public ResponseEntity<Messages> createQuestionInTestInCourse(@PathVariable Long courseId, @PathVariable Long testId, @RequestBody Question question) {
        CourseEntity courseEntity = courseServiceImpl.get(courseId);
        if (courseEntity == null) return new ResponseEntity<>(COURSE_DOES_NOT_EXIST,HttpStatus.BAD_REQUEST);
        Optional<TestEntity> optionalTest = courseEntity.getTests().stream().filter(test -> test.getId().equals(testId)).findAny();
        if (!optionalTest.isPresent()) return new ResponseEntity<>(TEST_NOT_EXIST,HttpStatus.BAD_REQUEST);
        optionalTest.get().addQuestion(QuestionTransformRules.questionToQuestionEntity.apply(question));
        courseServiceImpl.save(courseEntity);
        return new ResponseEntity<>(TEST_ADDED,HttpStatus.OK);
    }
}