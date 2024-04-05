package com.openclassrooms.mddapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
    
    @PostMapping()
    public ResponseEntity create(@RequestBody UserDto user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        
        userService.create(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody UserDto user) {
        User existingUser = userService.getUserById(id);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        
        userService.update(existingUser);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
