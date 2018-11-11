package com.test.rest;

import com.test.bysiness.suscribers.dto.SubscriberData;
import com.test.servicies.SubscriberService;
import com.test.utilities.Messages;
import com.test.utilities.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.test.utilities.Messages.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final SubscriberService subscriberService;

    @RequestMapping(value = "rest/is-login-free", method = RequestMethod.POST)
    public ResponseEntity<Messages> isLoginUnique(@RequestBody String login) {
        Optional<SubscriberData> subscriber = subscriberService.findSubscriber(login);
        if (subscriber.isPresent()) return new ResponseEntity<>(LOGIN_IS_FREE, HttpStatus.OK);
        return new ResponseEntity<>(USER_ALREADY_EXISTS, HttpStatus.OK);
    }

    @RequestMapping(value = "rest/is-email-free", method = RequestMethod.POST)
    public ResponseEntity<Messages> isEmailUnique(@RequestBody String email) {
        Optional<SubscriberData> subscriber = subscriberService.findSubscriberByEmail(email);
        if (subscriber.isPresent()) return new ResponseEntity<>(EMAIL_IS_FREE, HttpStatus.OK);
        return new ResponseEntity<>(USER_EMAIL_ALREADY_EXISTS, HttpStatus.OK);
    }


    @RequestMapping(value = "rest/user/by-login", method = RequestMethod.POST)
    public ResponseEntity<SubscriberData> findSubscriberByLogin(@RequestBody String login) {
        Optional<SubscriberData> subscriber = subscriberService.findSubscriber(login);
        return subscriber.map(subscriberData -> new ResponseEntity<>(subscriberData, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "rest/user/by-id", method = RequestMethod.POST)
    public ResponseEntity<SubscriberData> findSubscriberById(@RequestBody Long id) {
        Optional<SubscriberData> subscriber = subscriberService.findSubscriber(id);
        return subscriber.map(subscriberData -> new ResponseEntity<>(subscriberData, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "rest/user/by-email", method = RequestMethod.POST)
    public ResponseEntity<SubscriberData> findSubscriber(@RequestBody String email) {
        Optional<SubscriberData> subscriber = subscriberService.findSubscriberByEmail(email);
        return subscriber.map(subscriberData -> new ResponseEntity<>(subscriberData, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "rest/create-user", method = RequestMethod.POST)
    public ResponseEntity<Messages> createUser(@RequestBody SubscriberData subscriberData, @RequestBody String password) {
        Optional<SubscriberData> savedUser = subscriberService.saveNewUser(subscriberData, password);
        if (savedUser.isPresent()) {
            return new ResponseEntity<>(USER_WAS_CREATED, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(USER_WAS_NOT_CREATED, HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "rest/change-role", method = RequestMethod.POST)
    public ResponseEntity changeRole(@RequestBody String login, @RequestBody String password, @RequestBody Roles role) {
        boolean wasChanged = subscriberService.changeUserRole(login, password, role);
        if (wasChanged) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "rest/delete-user", method = RequestMethod.POST)
    public ResponseEntity deleteUser(@RequestBody Long id, @RequestBody String password) {
        boolean wasDeleted = subscriberService.delete(id, password);
        if (wasDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
//    @RequestMapping(value = "rest/createUser", method = RequestMethod.POST)
//    public ResponseEntity<Messages> subscribeUserOnCourse(@RequestBody String login, @RequestBody Long courseId) {
//        UserEntity userEntity = userServiceImpl.find(login);
//        if (userEntity == null) return new ResponseEntity<>(USER_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
//        CourseEntity courseEntity = courseServiceImpl.get(courseId);
//        if (courseEntity == null) return new ResponseEntity<>(COURSE_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
//        userEntity.subscribe(courseEntity);
//        return new ResponseEntity<>(USER_ALREADY_EXISTS, HttpStatus.OK);
//    }
}
