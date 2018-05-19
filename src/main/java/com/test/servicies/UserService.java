package com.test.servicies;

import com.test.bysiness.User;
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

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    User get(Integer id) {
        return userRepository.findOne(id);
    }

    User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }

    void delete(User user) {
        userRepository.delete(user);
    }

}
