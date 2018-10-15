package com.test.servicies;

import com.test.bysiness.dto.AnswerData;
import com.test.bysiness.dto.PassedTest;
import com.test.bysiness.dto.SubscriberData;
import com.test.bysiness.entities.CourseProgressEntity;
import com.test.bysiness.entities.UserEntity;
import com.test.bysiness.utilities.Roles;

import java.util.List;

public interface UserService {
    UserEntity get(Long id);

    SubscriberData getDTO(Long id);

    UserEntity save(UserEntity userEntity);

    SubscriberData saveNewUser(SubscriberData subscriber, String password);

    String changeUserRole(SubscriberData subscriber, String password, Roles role);

    boolean subscribeUserOnToCourse(String login, Long courseId);

    UserEntity find(String login);

    SubscriberData findSubscriber(String login);

    void delete(Long id);

    List<CourseProgressEntity> getUserProgress(Long userId);

    boolean passNewTest(String login, Long courseProgressId, PassedTest passedTest);

    boolean answerSomeQuestion(String login, Long passedTestId, AnswerData answer);
}
