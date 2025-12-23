package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    // The test calls userService.register(user)
    User register(User user);
    
    // The test also expects findByEmail
    User findByEmail(String email);
    
    // Add other methods as needed
    boolean validatePassword(String rawPassword, String encodedPassword);
}