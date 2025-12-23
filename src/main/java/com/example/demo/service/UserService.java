package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    
    List<User> getAllUsers();
    
    Optional<User> getUserById(Long id);
    
    Optional<User> getUserByUsername(String username);
    
    User updateUser(Long id, User userDetails);
    
    void deleteUser(Long id);
    
    // Add this method if it's in your interface
    boolean validatePassword(String rawPassword, String encodedPassword);
}