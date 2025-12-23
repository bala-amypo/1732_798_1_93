package com.example.demo.service;

import com.example.demo.model.User;

public interface AuthService {
    
    User register(User user);
    
    User authenticate(String username, String password);
    
    String generateToken(String username);
    
    boolean validateToken(String token);
    
    // Add this method if it's in your interface
    boolean validatePassword(String rawPassword, String encodedPassword);
}