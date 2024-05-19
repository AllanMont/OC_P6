package com.openclassrooms.mddapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




/**
 * Controller class for handling user-related HTTP requests.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Constructor for UserController.
     * @param userService The UserService instance to be injected.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * Endpoint to retrieve a user's profile by ID.
     * @param id The ID of the user to retrieve.
     * @return ResponseEntity containing the user's profile if found, or 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User userFind = userService.getUserById(id);

        if (userFind != null) {
            return ResponseEntity.ok(userFind);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint to update a user's profile.
     * @param user The updated user profile.
     * @param authentication The Authentication object representing the current authenticated user.
     * @return ResponseEntity containing the updated user profile if successful, or 404 if user is not found.
     */
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user, Authentication authentication) {
        Integer userId = userService.getUserIdByName(authentication);
        
        if(userId == null) {
            return ResponseEntity.notFound().build();
        }

        if(user.getEmail() == null) {
            user.setEmail(userService.getUserById(userId).getEmail());
        }
        if(user.getName() == null) {
            user.setName(userService.getUserById(userId).getName());
        }
        user.setId(userId);
        user.setPassword(userService.getUserById(userId).getPassword());
        user.setCreatedAt(userService.getUserById(userId).getCreatedAt());
        
        User userUpdated = userService.updateUser(user);

        if (userUpdated != null) {
            return ResponseEntity.ok(userUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
