package com.test.servicies.impl;

import com.test.bysiness.dto.AnswerData;
import com.test.bysiness.dto.PassedTest;
import com.test.bysiness.dto.SubscriberData;
import com.test.bysiness.entities.*;
import com.test.bysiness.functions.QuestionTransformRules;
import com.test.bysiness.functions.UserTransformRules;
import com.test.bysiness.utilities.Roles;
import com.test.repositories.*;
import com.test.servicies.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final CourseProgressRepository courseProgressRepository;
    private final PassedTestRepository passedTestRepository;
    private final QuestionRepository questionRepository;

    public UserEntity get(Long id) {
        return userRepository.findOne(id);
    }

    public SubscriberData getDTO(Long id) {
        return UserTransformRules.userEntityToSubscriber.apply(userRepository.findOne(id));
    }

    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public SubscriberData saveNewUser(SubscriberData subscriber, String password) {
        UserEntity userEntity = userRepository.findByLogin(subscriber.getLogin());
        if (userEntity != null) return null;
        userEntity = userRepository.findByEmail(subscriber.getEmail());
        if (userEntity != null) return null;
        userEntity = new UserEntity();
        userEntity.setLogin(subscriber.getLogin());
        userEntity.setEmail(subscriber.getEmail());
        userEntity.setPasswordHash(new BCryptPasswordEncoder().encode(password));
        userEntity.setRole(Roles.User);
        userEntity.setSubscribedCourses(new HashSet<>());
        userEntity = userRepository.save(userEntity);
        return UserTransformRules.userEntityToSubscriber.apply(userEntity);
    }

    public String changeUserRole(SubscriberData subscriber, String password, Roles role) {
        UserEntity userEntity = userRepository.findByLogin(subscriber.getLogin());
        if (!userEntity.getPasswordHash().equals(new BCryptPasswordEncoder().encode(password))) return "Wrong password";
        userEntity.setRole(role);
        return "Ok";
    }

    public boolean subscribeUserOnToCourse(String login, Long courseId) {
        UserEntity userEntity = userRepository.findByLogin(login);
        if (userEntity == null) return false;
        CourseEntity courseEntity = courseRepository.findOne(courseId);
        if (courseEntity == null) return false;
        userEntity.subscribe(courseEntity);
        userRepository.save(userEntity);
        return true;
    }

    public UserEntity find(String login) {
        return userRepository.findByLogin(login);
    }

    public SubscriberData findSubscriber(String login) {
        return UserTransformRules.userEntityToSubscriber.apply(userRepository.findByLogin(login));
    }

    public void delete(Long id) {
        courseProgressRepository.deleteAllBySuscriberId(id);
        userRepository.delete(id);
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

    public void delete(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }

    public List<CourseProgressEntity> getUserProgress(Long userId) {
        return userRepository.getUserProgress(userId);
    }

    public boolean passNewTest(String login, Long courseProgressId, PassedTest passedTest) {
        UserEntity user = userRepository.findByLogin(login);
        Optional<CourseProgressEntity> courseProgress = userRepository.getUserProgress(user.getId())
                .stream()
                .filter(pr -> pr.getCourse().getId().equals(courseProgressId))
                .findAny();
        if (!courseProgress.isPresent()) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        PassedTestEntity test = new PassedTestEntity();
        test.setLastChangeDate(now);
        test.setStartDate(now);
        courseProgress.ifPresent(cp -> cp.addPassedTest(test));
        return true;
    }

    public boolean answerSomeQuestion(String login, Long passedTestId, AnswerData answer) {
        UserEntity user = userRepository.findByLogin(login);
        PassedTestEntity test = passedTestRepository.findOne(passedTestId);
        QuestionEntity question = questionRepository.findOne(answer.getQuestionId());
        if (user == null || test == null || question == null) return false;
        AnswerEntity entity = QuestionTransformRules.answerToEmptyAnswerEntity.apply(answer);
        entity.setParentPassedTest(test);
        entity.setQuestionToAnswer(question);
        test.addAnswer(entity);
        return true;
    }
}
