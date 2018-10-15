package com.test.rest;

import com.test.bysiness.entities.CourseEntity;
import com.test.bysiness.entities.UserEntity;
import com.test.bysiness.utilities.Messages;
import com.test.servicies.impl.CourseServiceImpl;
import com.test.servicies.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.test.bysiness.utilities.Messages.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final CourseServiceImpl courseServiceImpl;

    @RequestMapping(value = "rest/userUnique", method = RequestMethod.POST)
    public ResponseEntity<Messages> findTestById(@RequestBody String login) {
        UserEntity userEntity = userServiceImpl.find(login);
        if (userEntity == null) return new ResponseEntity<>(LOGIN_IS_FREE, HttpStatus.OK);
        return new ResponseEntity<>(USER_ALREADY_EXISTS, HttpStatus.OK);
    }

    @RequestMapping(value = "rest/suscribe", method = RequestMethod.POST)
    public ResponseEntity<Messages> subscribeUserOnCourse(@RequestBody String login, @RequestBody Long courseId) {
        UserEntity userEntity = userServiceImpl.find(login);
        if (userEntity == null) return new ResponseEntity<>(USER_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
        CourseEntity courseEntity = courseServiceImpl.get(courseId);
        if (courseEntity == null) return new ResponseEntity<>(COURSE_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
        userEntity.subscribe(courseEntity);
        return new ResponseEntity<>(USER_ALREADY_EXISTS, HttpStatus.OK);
    }
}
