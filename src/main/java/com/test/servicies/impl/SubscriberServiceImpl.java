package com.test.servicies.impl;

import com.test.bysiness.creation.entities.CourseEntity;
import com.test.bysiness.creation.entities.TestEntity;
import com.test.bysiness.suscribers.dto.SubscriberData;
import com.test.bysiness.suscribers.entities.UserEntity;
import com.test.bysiness.usage.entities.CourseProgressEntity;
import com.test.functions.UserTransformRules;
import com.test.repositories.CourseRepository;
import com.test.repositories.TestRepository;
import com.test.repositories.UserRepository;
import com.test.servicies.SuscriberService;
import com.test.utilities.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SuscriberService, UserDetailsService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final TestRepository testRepository;
    private final PasswordEncoder encoder;

    @Override
    public Optional<SubscriberData> saveNewUser(SubscriberData subscriber, String password) {
        if (userRepository.findByLogin(subscriber.getLogin()) != null) return Optional.empty();
        if (userRepository.findByEmail(subscriber.getEmail()) != null) return Optional.empty();
        UserEntity newUserEntity = UserTransformRules.subscriberToNewEntity.apply(subscriber);
        newUserEntity.setPasswordHash(encoder.encode(password));
        newUserEntity = userRepository.save(newUserEntity);
        return Optional.ofNullable(UserTransformRules.userEntityToSubscriber.apply(newUserEntity));
    }

    @Override
    public boolean changeUserRole(String login, String password, Roles role) {
        UserEntity userByLogin = userRepository.findByLogin(login);
        if (userByLogin == null) return false;
        if (encoder.matches(password, userByLogin.getPasswordHash())) {
            userByLogin.setRole(role);
            userRepository.save(userByLogin);
            return true;
        }
        return false;
    }

    @Override
    public Optional<SubscriberData> findSubscriber(String login) {
        UserEntity userByLogin = userRepository.findByLogin(login);
        if (userByLogin == null) {
            return Optional.empty();
        }
        return Optional.of(UserTransformRules.userEntityToSubscriber.apply(userByLogin));
    }

    @Override
    public Optional<SubscriberData> findSubscriber(Long id) {
        UserEntity userById = userRepository.findOne(id);
        if (userById == null) {
            return Optional.empty();
        }
        return Optional.of(UserTransformRules.userEntityToSubscriber.apply(userById));
    }

    @Override
    public void delete(Long id) {
        UserEntity userById = userRepository.findOne(id);
        if (userById != null) userRepository.delete(userById);
    }

    @Override
    public Optional<Set<CourseProgressEntity>> getUserProgress(Long userId) {
        UserEntity userById = userRepository.findOne(userId);
        if (userById == null) {
            return Optional.empty();
        }
        return Optional.of(userById.getSubscribedCourses());
    }

    @Override
    public boolean subscribeUserOnToCourse(String login, Long courseId) {
        UserEntity userByLogin = userRepository.findByLogin(login);
        if (userByLogin == null) return false;
        CourseEntity course = courseRepository.findOne(courseId);
        if (course == null) return false;
        Set<CourseProgressEntity> usersCourseProgresses = userRepository.findUsersCourseProgresses(userByLogin.getId());
        Optional<CourseEntity> alreadySubscribed = usersCourseProgresses
                .stream()
                .map(CourseProgressEntity::getCourse)
                .filter(subscribedCourse -> Objects.nonNull(subscribedCourse.getId()))
                .filter(subscribedCourse -> subscribedCourse.getId().equals(courseId))
                .findAny();
        if (alreadySubscribed.isPresent()) return true;
        userByLogin.subscribeCourse(course);
        userRepository.save(userByLogin);
        return false;
    }

    @Override
    public boolean startNewTest(String login, Long courseId, Long testId) {
        TestEntity test = testRepository.findOne(testId);
        if (test == null) return false;
        UserEntity user = userRepository.findByLogin(login);
        if (user == null) return false;
        return false;//TODO: finish
    }

    @Override
    public boolean answerSomeQuestion(String login, Long questionId) {
        return false;//TODO: finish
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByLogin(login);
        if (userEntity != null) {
            Collection<GrantedAuthority> authorityes = new ArrayList<>();
            authorityes.add(new SimpleGrantedAuthority(userEntity.getRole().getName()));
            return new User(userEntity.getLogin(), userEntity.getPasswordHash(), authorityes);
        } else {
            return User.withUsername(login)
                    .password("")
                    .authorities("")
                    .accountExpired(true)
                    .accountLocked(true)
                    .disabled(true).build();
        }
    }
}
