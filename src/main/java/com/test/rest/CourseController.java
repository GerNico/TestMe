package com.test.rest;

import com.test.bysiness.dto.Course;
import com.test.bysiness.dto.Question;
import com.test.bysiness.dto.Test;
import com.test.bysiness.entities.CourseEntity;
import com.test.bysiness.entities.QuestionEntity;
import com.test.bysiness.entities.TestEntity;
import com.test.bysiness.functions.QuestionTransformRules;
import com.test.bysiness.functions.TestTransformRules;
import com.test.servicies.CourseService;
import com.test.servicies.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CourseController {

    private final CourseService courseService;
    private final TestService testService;

    @Autowired
    public CourseController(CourseService courseService, TestService testService) {
        this.courseService = courseService;
        this.testService = testService;
    }

    @RequestMapping(value = "rest/courses", method = RequestMethod.POST)
    public ResponseEntity createCourse(@RequestBody Course course) {
        courseService.createNewCourse(course);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "rest/course/{id}", method = RequestMethod.GET)
    public ResponseEntity<Course> findCourse(@PathVariable Long id) {
        Course course = courseService.getCourseDTO(id);
        if (course == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @RequestMapping(value = "rest/course/{id}/tests", method = RequestMethod.POST)
    public ResponseEntity createTestForCourse(@PathVariable Long id, @RequestBody Test test) {
        CourseEntity courseEntity = courseService.get(id);
        if (courseEntity == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        courseEntity.addTest(TestTransformRules.testToTestEntity.apply(test));
        courseService.save(courseEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "rest/test/{id}", method = RequestMethod.GET)
    public ResponseEntity<Test> findTestById(@PathVariable Long id) {
        TestEntity testEntity = testService.get(id);
        if (testEntity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(TestTransformRules.testEntityToTest.apply(testEntity),HttpStatus.OK);
    }

    @RequestMapping(value = "rest/course/{courseId}/test/{testId}/questions", method = RequestMethod.POST)
    public ResponseEntity createQuestionInTestInCourse(@PathVariable Long courseId, @PathVariable Long testId, @RequestBody Question question) {
        CourseEntity courseEntity = courseService.get(courseId);
        if (courseEntity == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Optional<TestEntity> optionalTest = courseEntity.getTests().stream().filter(test -> test.getId().equals(testId)).findAny();
        if (!optionalTest.isPresent()) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        optionalTest.get().addQuestion(QuestionTransformRules.questionToQuestionEntity.apply(question));
        courseService.save(courseEntity);
        return new ResponseEntity(HttpStatus.OK);
    }
}