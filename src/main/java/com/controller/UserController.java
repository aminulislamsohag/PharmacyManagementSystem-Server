package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.model.User;
import com.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
    private UserService userService;

    @PostMapping("/login")
    public int login(@RequestBody User user) {
        return userService.login(user);
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
    
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
    
    

}
