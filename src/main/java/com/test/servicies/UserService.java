package com.test.servicies;

import com.test.bysiness.dto.Subscriber;
import com.test.bysiness.entities.UserEntity;
import com.test.bysiness.utilities.Roles;

public interface UserService {
    UserEntity get(Long id);

    Subscriber getDTO(Long id);

    UserEntity save(UserEntity userEntity);

    Subscriber saveNewUser(Subscriber subscriber, String password);

    String changeUserRole(Subscriber subscriber, String password, Roles role);

    boolean subscribeUserOnToCourse(String login, Long courseId);

    UserEntity find(String login);

    Subscriber findSubscriber(String login);

    void delete(Long id);
}
