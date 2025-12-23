package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User register(User user);  // For test compatibility
    User registerUser(User user);
    User findByEmail(String email);
    boolean validatePassword(String rawPassword, String encodedPassword);
}