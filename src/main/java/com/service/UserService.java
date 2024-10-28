package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.model.User;
import com.repository.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {
        // Hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String login(User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser == null) return "USER_NOT_FOUND";
        if (!passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) return "WRONG_PASSWORD";
        		return foundUser.getRole();
    }
    
    
    public User resetPassword(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(existingUser);
        } else {
            return null; // User not found
        }
    }

    
    
    
 

}