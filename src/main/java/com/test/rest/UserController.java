package com.test.rest;

import com.test.bysiness.suscribers.dto.SubscriberData;
import com.test.servicies.SubscriberService;
import com.test.utilities.Messages;
import com.test.utilities.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.test.utilities.Messages.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "rest/user")
public class UserController {
    private final SubscriberService subscriberService;

    @GetMapping(value = "/is-login-free/{login}")
    public ResponseEntity<Messages> isLoginUnique(@PathVariable String login) {
        Optional<SubscriberData> subscriber = subscriberService.findSubscriber(login);
        if (subscriber.isPresent()) return new ResponseEntity<>(LOGIN_IS_FREE, HttpStatus.OK);
        return new ResponseEntity<>(USER_ALREADY_EXISTS, HttpStatus.OK);
    }

    @GetMapping(value = "/is-email-free/{email}")
    public ResponseEntity<Messages> isEmailUnique(@PathVariable String email) {
        Optional<SubscriberData> subscriber = subscriberService.findSubscriberByEmail(email);
        if (subscriber.isPresent()) return new ResponseEntity<>(EMAIL_IS_FREE, HttpStatus.OK);
        return new ResponseEntity<>(USER_EMAIL_ALREADY_EXISTS, HttpStatus.OK);
    }


    @GetMapping(value = "/by-login/{login}")
    public ResponseEntity<SubscriberData> findSubscriberByLogin(@PathVariable String login) {
        Optional<SubscriberData> subscriber = subscriberService.findSubscriber(login);
        return subscriber.map(subscriberData -> new ResponseEntity<>(subscriberData, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/by-id/{id}")
    public ResponseEntity<SubscriberData> findSubscriberById(@PathVariable Long id) {
        Optional<SubscriberData> subscriber = subscriberService.findSubscriber(id);
        return subscriber.map(subscriberData -> new ResponseEntity<>(subscriberData, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/by-email/{email}")
    public ResponseEntity<SubscriberData> findSubscriber(@PathVariable String email) {
        Optional<SubscriberData> subscriber = subscriberService.findSubscriberByEmail(email);
        return subscriber.map(subscriberData -> new ResponseEntity<>(subscriberData, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/create-user")
    public ResponseEntity<Messages> createUser(@RequestBody SubscriberData subscriberData, @RequestBody String password) {
        Optional<SubscriberData> savedUser = subscriberService.saveNewUser(subscriberData, password);
        if (savedUser.isPresent()) {
            return new ResponseEntity<>(USER_WAS_CREATED, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(USER_WAS_NOT_CREATED, HttpStatus.NOT_MODIFIED);
        }
    }

    @PutMapping(value = "/change-role")
    public ResponseEntity changeRole(@RequestBody String login, @RequestBody String password, @RequestBody Roles role) {
        boolean wasChanged = subscriberService.changeUserRole(login, password, role);
        if (wasChanged) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value = "/delete-user")
    public ResponseEntity deleteUser(@RequestBody Long id, @RequestBody String password) {
        boolean wasDeleted = subscriberService.delete(id, password);
        if (wasDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
}
