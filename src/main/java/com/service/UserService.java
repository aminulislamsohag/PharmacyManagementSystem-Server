package com.service;


import java.util.ArrayList;
import java.util.List;

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

    public User addUser(User user) {
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

    public String getUserRole(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }
        return user.getRole();
    }

    public void updateUserRole(String username, String newRole) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }
        user.setRole(newRole);
        userRepository.save(user);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public void deleteuser(Long id) {
        if (userRepository.existsById(id)) {
        	userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Chategory not found with ID: " + id);
        }
    }
    
    public List<User> searchUser(String query) {
        List<User> result = new ArrayList<>();
        
        try {
            // Attempt to parse the query as an integer to search by userid
            int userid = Integer.parseInt(query);
            result.addAll(userRepository.findByUserid(userid));
        } catch (NumberFormatException e) {
            // If parsing fails, treat the query as a username search
            result.addAll(userRepository.findByUsernameContaining(query));
        }
        
        return result;
    }
    
    
    
 

}