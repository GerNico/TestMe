package com.test.servicies;

import com.test.bysiness.dto.Subscriber;
import com.test.bysiness.entities.CourseEntity;
import com.test.bysiness.entities.UserEntity;
import com.test.bysiness.functions.UserTransformRules;
import com.test.bysiness.utilities.Roles;
import com.test.repositories.CourseRepository;
import com.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private CourseRepository courseRepository;

    public UserService() {
    }

    @Autowired
    public UserService(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    UserEntity get(Long id) {
        return userRepository.findOne(id);
    }

    Subscriber getDTO(Long id) {
        return UserTransformRules.userEntityToSubscriber.apply(userRepository.findOne(id));
    }

    UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    Subscriber saveNewUser(Subscriber subscriber, String password) {
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

    String changeUserRole(Subscriber subscriber, String password, Roles role) {
        UserEntity userEntity = userRepository.findByLogin(subscriber.getLogin());
        if (!userEntity.getPasswordHash().equals(new BCryptPasswordEncoder().encode(password))) return "Wrong password";
        userEntity.setRole(role);
        userEntity = userRepository.save(userEntity);
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

    public Subscriber findSubscriber(String login) {
        return UserTransformRules.userEntityToSubscriber.apply(userRepository.findByLogin(login));
    }

    public void delete(Long id) {
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
}
