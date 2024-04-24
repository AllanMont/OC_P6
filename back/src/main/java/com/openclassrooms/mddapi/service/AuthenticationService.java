package com.openclassrooms.mddapi.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.LoginRequest;
import com.openclassrooms.mddapi.dto.RegisterRequest;
import com.openclassrooms.mddapi.model.Authentication;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public Authentication register(RegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }
        var user = User
            .builder()
            .name(request.getName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();

        userRepository.save(user);

        Map<String, String> jwtToken = jwtService.generateToken(user);

        return Authentication
            .builder()
            .token(jwtToken.get("token"))
            .build();
    }

    public Authentication login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            var user = userRepository.findByEmail(request.getEmail());
            Map<String, String> jwtToken = jwtService.generateToken(user);

            return Authentication
                .builder()
                .token(jwtToken.get("token"))
                .build();
                
        } catch (Exception e) {
            throw new RuntimeException("Invalid email/password supplied");
        }
    }

    public User getLoggedInUser(String name) {
        return userRepository.findByName(name);
    }

    public User getUserById(Integer id) {
        if(!userRepository.findById(id).isPresent()) {
            throw new RuntimeException("User not found");
        }
        return userRepository.findById(id).get();
    }
}
