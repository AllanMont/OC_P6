package com.openclassrooms.mddapi.service;

import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public Integer getUserIdByName(Authentication authentication) {
        User userFind = userRepository.findByName(authentication.getName());
        if (userFind == null) {
            return null;
        }
        return userFind.getId();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean checkPassword(User user, String password) {
		return user.getPassword().equals(password);
	}

	public boolean isUserExistByEmail(String email) {
		return userRepository.findByEmail(email) != null;
	}

	public User createUser(String name, String email, String password) {
		return userRepository.save(User.builder().name(name).email(email).password(password).createdAt(LocalDateTime.now()).build());
	}
}
