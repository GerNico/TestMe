package com.test.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CourseCreationController {

//    private final CourseServiceImpl courseServiceImpl;
//
//    @RequestMapping(value = "rest/courses", method = RequestMethod.POST)
//    public ResponseEntity<CourseData> createCourse(@RequestBody CourseData courseFromRequest) {
//        CourseData course = courseServiceImpl.createNewCourse(courseFromRequest);
//        return new ResponseEntity<>(course, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "rest/courses", method = RequestMethod.GET)
//    public ResponseEntity<List<CourseInfo>> getInfoAboutAllCourses() {
//        return new ResponseEntity<>(courseServiceImpl.getAllCoursesInfo().collect(Collectors.toList()), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "rest/course/{id}", method = RequestMethod.GET)
//    public ResponseEntity<CourseData> findCourse(@PathVariable Long id) {
//        CourseData course = courseServiceImpl.getCourseDTO(id);
//        if (course == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(course, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "rest/course/{id}/tests", method = RequestMethod.POST)
//    public ResponseEntity<Messages> createTestForCourse(@PathVariable Long id, @RequestBody TestData test) {
//        CourseEntity courseEntity = courseServiceImpl.get(id);
//        if (courseEntity == null) return new ResponseEntity<>(Messages.COURSE_DOES_NOT_EXIST,HttpStatus.NOT_FOUND);
//        courseEntity.addTest(TestTransformRules.testToTestEntity.apply(test));
//        courseServiceImpl.save(courseEntity);
//        return new ResponseEntity<>(TEST_ADDED,HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "rest/course/{courseId}/test/{testId}/questions", method = RequestMethod.POST)
//    public ResponseEntity<Messages> createQuestionInTestInCourse(@PathVariable Long courseId, @PathVariable Long testId, @RequestBody QuestionData question) {
//        CourseEntity courseEntity = courseServiceImpl.get(courseId);
//        if (courseEntity == null) return new ResponseEntity<>(COURSE_DOES_NOT_EXIST,HttpStatus.BAD_REQUEST);
//        Optional<TestEntity> optionalTest = courseEntity.getTests().stream().filter(test -> test.getId().equals(testId)).findAny();
//        if (!optionalTest.isPresent()) return new ResponseEntity<>(TEST_NOT_EXIST,HttpStatus.BAD_REQUEST);
//        optionalTest.get().addQuestion(QuestionTransformRules.questionToQuestionEntity.apply(question));
//        courseServiceImpl.save(courseEntity);
//        return new ResponseEntity<>(TEST_ADDED,HttpStatus.OK);
//    }
}