package com.test.servicies.impl;

import com.test.bysiness.creation.entities.CourseEntity;
import com.test.bysiness.creation.entities.TestEntity;
import com.test.bysiness.suscribers.dto.SubscriberData;
import com.test.bysiness.suscribers.entities.UserEntity;
import com.test.bysiness.usage.entities.CourseProgressEntity;
import com.test.bysiness.usage.entities.PassedTestEntity;
import com.test.functions.UserTransformRules;
import com.test.repositories.CourseRepository;
import com.test.repositories.TestRepository;
import com.test.repositories.UserRepository;
import com.test.servicies.SubscriberService;
import com.test.utilities.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

import static com.test.functions.UserTransformRules.userEntityToSubscriber;
@Service
@Transactional
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService, UserDetailsService {

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
        return Optional.ofNullable(userEntityToSubscriber.apply(newUserEntity));
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
        return Optional.of(userEntityToSubscriber.apply(userByLogin));
    }

    @Override
    public Optional<SubscriberData> findSubscriberByEmail(String email) {
        UserEntity userByLogin = userRepository.findByEmail(email);
        if (userByLogin == null) {
            return Optional.empty();
        }
        return Optional.of(userEntityToSubscriber.apply(userByLogin));
    }

    @Override
    public Optional<SubscriberData> findSubscriber(Long id) {
        UserEntity userById = userRepository.findOne(id);
        if (userById == null) {
            return Optional.empty();
        }
        return Optional.of(userEntityToSubscriber.apply(userById));
    }

    @Override
    public boolean delete(Long id, String password) {
        UserEntity userById = userRepository.findOne(id);
        if (userById != null && encoder.matches(password, userById.getPasswordHash())) {
            userRepository.delete(userById);
            return true;
        }
        return false;
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
    public boolean startNewTest(String login, Long testId) {
        TestEntity test = testRepository.findOne(testId);
        if (test == null) return false;
        CourseEntity course = test.getParentCourse();
        if (course == null) return false;
        UserEntity user = userRepository.findByLogin(login);
        if (user == null) return false;
        Optional<CourseProgressEntity> requiredSubscription = user.getSubscribedCourses()
                .stream()
                .filter(subscription -> subscription.getCourse().getId().equals(course.getId()))
                .findAny();
        if (!requiredSubscription.isPresent()) return false;
        CourseProgressEntity courseProgress = requiredSubscription.get();
        Optional<PassedTestEntity> testIfExists = courseProgress.getPassedTests()
                .stream()
                .filter(passedTest -> passedTest.getBasedTest().getId().equals(testId))
                .findAny();
        if (testIfExists.isPresent()) return true;
        PassedTestEntity passedTestEntity = new PassedTestEntity();
        passedTestEntity.setPassingDate(LocalDateTime.now());
        passedTestEntity.setLastChangeDate(LocalDateTime.now());
        passedTestEntity.setBasedTest(test);
        requiredSubscription.ifPresent(subscription -> subscription.addPassedTest(passedTestEntity));
        return true;
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

    @Override
    public boolean hasAuthority(GrantedAuthority authority) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().contains(authority);
    }

    @Override
    public SubscriberData getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userEntityToSubscriber.apply(userRepository.findByLogin(auth.getName()));
    }
}
