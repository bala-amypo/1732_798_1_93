package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User registerUser(User user);
    User findByEmail(String email);
    boolean validatePassword(String rawPassword, String encodedPassword);
}