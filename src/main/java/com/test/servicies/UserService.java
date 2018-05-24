package com.test.servicies;

import com.test.bysiness.entities.UserEntity;
import com.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

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

}
