package com.openclassrooms.mddapi.service;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public void create(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        userRepository.save(user);
    }
}
