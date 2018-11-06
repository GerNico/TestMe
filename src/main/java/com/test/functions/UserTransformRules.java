package com.test.functions;

import com.test.bysiness.creation.entities.CourseEntity;
import com.test.bysiness.suscribers.dto.SubscriberData;
import com.test.bysiness.suscribers.entities.UserEntity;
import com.test.bysiness.usage.entities.CourseProgressEntity;
import com.test.utilities.Roles;

import java.util.function.Function;
import java.util.stream.Collectors;

public class UserTransformRules {

    public static Function<UserEntity, SubscriberData> userEntityToSubscriber = userEntity -> {
        SubscriberData subscriber = new SubscriberData();
        subscriber.setId(userEntity.getId());
        subscriber.setLogin(userEntity.getLogin());
        subscriber.setEmail(userEntity.getEmail());
        subscriber.setMyCoursesIds(
                userEntity.getMyCourses().stream()
                        .map(CourseEntity::getId)
                        .collect(Collectors.toSet())
        );
        subscriber.setSubscribedCourseIds(
                userEntity.getSubscribedCourses().stream()
                        .map(CourseProgressEntity::getCourse)
                        .map(CourseEntity::getId)
                        .collect(Collectors.toSet())
        );
        return subscriber;
    };

    public static Function<SubscriberData, UserEntity> subscriberToNewEntity = subscriber -> {
        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setLogin(subscriber.getLogin());
        newUserEntity.setEmail(subscriber.getEmail());
        newUserEntity.setRole(Roles.User);
        return newUserEntity;
    };
}