package com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.model.User;
import com.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
    private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
	    String role = userService.login(user);
	    if ("USER_NOT_FOUND".equals(role)) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	    if ("WRONG_PASSWORD".equals(role)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password");
	    return ResponseEntity.ok(role); // Return the role if login is successful
	}
    
    @PutMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody User user) {
        User updatedUser = userService.resetPassword(user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        if (savedUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving user");
        }
    }
    
    @GetMapping("/{username}/role")
    public ResponseEntity<String> getUserRole(@PathVariable String username) {
        try {
            String role = userService.getUserRole(username);
            return ResponseEntity.ok(role);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{username}/role")
    public ResponseEntity<String> updateUserRole(@PathVariable String username, @RequestBody Map<String, String> roleData) {
        try {
            String newRole = roleData.get("newRole");  // Extract newRole from the map
            userService.updateUserRole(username, newRole);
            return ResponseEntity.ok("User role updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    

}
