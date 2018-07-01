package com.test.bysiness.functions;

import com.test.bysiness.dto.Subscriber;
import com.test.bysiness.entities.CourseEntity;
import com.test.bysiness.entities.UserEntity;

import java.util.function.Function;
import java.util.stream.Collectors;

public class UserTransformRules {

    public static Function<UserEntity, Subscriber> userEntityToSubscriber = userEntity -> {
        Subscriber subscriber = new Subscriber();
        subscriber.setId(userEntity.getId());
        subscriber.setLogin(userEntity.getLogin());
        subscriber.setEmail(userEntity.getEmail());
        subscriber.setCoursesId(
                userEntity.getSubscribedCourses().stream()
                        .map(CourseEntity::getId)
                        .collect(Collectors.toSet()));
        return subscriber;
    };
}