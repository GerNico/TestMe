package com.test.rest;

import com.test.bysiness.entities.CourseEntity;
import com.test.bysiness.entities.QuestionEntity;
import com.test.bysiness.entities.TestEntity;
import com.test.bysiness.entities.UserEntity;
import com.test.servicies.CourseService;
import com.test.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "rest/courses", method = RequestMethod.POST)
    public ResponseEntity createCourse(@RequestBody CourseEntity courseEntity) {
        courseService.save(courseEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "rest/course/{id}/tests", method = RequestMethod.POST)
    public ResponseEntity createTestForCourse(@PathVariable Long id, @RequestBody TestEntity testEntity) {

        CourseEntity courseEntity = courseService.get(id);
        if (courseEntity == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        courseEntity.addTest(testEntity);
        courseService.save(courseEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "rest/course/{courseId}/test/{testId}/questions", method = RequestMethod.POST)
    public ResponseEntity createQuestionInTestInCourse(@PathVariable Long courseId, @PathVariable Long testId, @RequestBody QuestionEntity questionEntity) {

        CourseEntity courseEntity = courseService.get(courseId);
        if (courseEntity == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Optional<TestEntity> optionalTest = courseEntity.getTests().stream().filter(test -> test.getId().equals(testId)).findAny();
        if (!optionalTest.isPresent()) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        optionalTest.get().addQuestion(questionEntity);
        courseService.save(courseEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @RequestMapping(value = "rest/courses", method = RequestMethod.POST)
//    public ResponseEntity createNewCourse(@RequestBody String courseName, @RequestBody String courseDescription) {
//        CourseEntity courseEntity = new CourseEntity();
//        courseEntity.setCourseName(courseName);
//        courseEntity.setCourseDescription(courseDescription);
//        courseService.save(courseEntity);
//        return new ResponseEntity(HttpStatus.OK);
//    }

//    private final UserService userService;
//
//    @Autowired
//    public CourseController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @RequestMapping(value = "/rest/users", method = RequestMethod.GET)
//    public ResponseEntity<List<UserEntity>> getTags() {
//        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/user/{login}", method = RequestMethod.GET)
//    public ResponseEntity<UserEntity> getTag(@PathVariable String login) {
//        return new ResponseEntity<>(userService.find(login), HttpStatus.OK);
//    }
}