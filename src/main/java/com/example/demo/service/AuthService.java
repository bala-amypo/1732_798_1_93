package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.model.User;

public interface AuthService {
    User registerUser(User user);
    User findByEmail(String email);
    boolean validatePassword(String rawPassword, String encodedPassword);
}