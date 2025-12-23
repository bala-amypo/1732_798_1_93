package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    
    List<User> getAllUsers();
    
    Optional<User> getUserById(Long id);
    
    Optional<User> getUserByUsername(String username);
    
    // Add this method for test compatibility
    Optional<User> findByEmail(String email);
    
    // Add this method for test compatibility
    User register(User user);
    
    User updateUser(Long id, User userDetails);
    
    void deleteUser(Long id);
    
    boolean validatePassword(String rawPassword, String encodedPassword);
}