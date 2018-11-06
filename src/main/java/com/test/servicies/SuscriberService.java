package com.test.servicies;

import com.test.bysiness.suscribers.dto.SubscriberData;
import com.test.bysiness.usage.entities.CourseProgressEntity;
import com.test.utilities.Roles;

import java.util.Optional;
import java.util.Set;

public interface SuscriberService {

    Optional<SubscriberData> saveNewUser(SubscriberData subscriber, String password);

    boolean changeUserRole(String login, String password, Roles role);

    Optional<SubscriberData> findSubscriber(String login);

    Optional<SubscriberData> findSubscriber(Long id);

    void delete(Long id);

    Optional<Set<CourseProgressEntity>> getUserProgress(Long userId);

    boolean subscribeUserOnToCourse(String login, Long courseId);

    boolean startNewTest(String login,Long courseId, Long testId);

    boolean answerSomeQuestion(String login, Long questionId);
}
