package com.test.servicies;

import com.test.bysiness.entities.UserEntity;
import com.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public UserService() {
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAll() {
        return new ArrayList<>(userRepository.findAll());
    }

    UserEntity get(Long id) {
        return userRepository.findOne(id);
    }

    UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

    void delete(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByLogin(login);
        if (userEntity != null) {
            Collection<GrantedAuthority> authorityes = new ArrayList<>();
            authorityes.add(new SimpleGrantedAuthority(userEntity.getRole()));
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
