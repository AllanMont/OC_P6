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




@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
	    User userFind = userService.getUserById(id);

	    if (userFind != null) {
	        return ResponseEntity.ok(userFind);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user,Authentication authentication) {
		Integer userId = userService.getUserIdByName(authentication);
		
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
