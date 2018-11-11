package com.test.servicies;

import com.test.bysiness.suscribers.dto.SubscriberData;
import com.test.bysiness.usage.entities.CourseProgressEntity;
import com.test.utilities.Roles;

import java.util.Optional;
import java.util.Set;

public interface SubscriberService {

    Optional<SubscriberData> saveNewUser(SubscriberData subscriber, String password);

    boolean changeUserRole(String login, String password, Roles role);

    Optional<SubscriberData> findSubscriber(String login);

    Optional<SubscriberData> findSubscriberByEmail(String email);

    Optional<SubscriberData> findSubscriber(Long id);

    boolean delete(Long id, String password);

    Optional<Set<CourseProgressEntity>> getUserProgress(Long userId);

    boolean subscribeUserOnToCourse(String login, Long courseId);

    boolean startNewTest(String login, Long testId);
}
