package com.openclassrooms.mddapi.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.LoginRequest;
import com.openclassrooms.mddapi.dto.RegisterRequest;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.AuthenticationService;
import com.openclassrooms.mddapi.service.JWTService;
import com.openclassrooms.mddapi.service.UserService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private JWTService jwtService;
    private UserService userService;
    private AuthenticationService authService;
    private ModelMapper modelMapper;

    public AuthController(JWTService jwtService, UserService userService, AuthenticationService authService, ModelMapper modelMapper) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> getToken(@RequestBody LoginRequest loginDetails) {
        String name = loginDetails.getName();
        String email = loginDetails.getEmail();
        String password = loginDetails.getPassword();
    
        User user = userService.getUserByEmail(email);
        if (user == null) {
            user = userService.getUserByName(name);
        }

        if (user == null || !userService.checkPassword(user, password)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Collections.singletonMap("error", "User not found or incorrect credentials"));
        }
    
        Map<String, String> tokenObject = jwtService.generateToken(user);
        return ResponseEntity.ok(tokenObject);
    }
    
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegisterRequest registrationDetails) {
        String email = registrationDetails.getEmail();
        String name = registrationDetails.getName();
        String password = registrationDetails.getPassword();
    
        boolean userExists = userService.isUserExistByEmail(email);
        if (userExists) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Collections.singletonMap("error", "User with this email already exists"));
        }
    
        try {
            User newUser = userService.createUser(name, email, password);
    
            Map<String, String> tokenObject = jwtService.generateToken(newUser);
    
            return ResponseEntity.ok(tokenObject);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getLoggedInUserInfo(Authentication authentication) {
        Integer userId = userService.getUserIdByName(authentication);

        User user = authService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        UserDto userDTO = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok(userDTO);
    }
}