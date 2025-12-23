package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    public User register(User user) {
        // For test compatibility - simple implementation
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    @Override
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            if (userDetails.getUsername() != null) {
                user.setUsername(userDetails.getUsername());
            }
            if (userDetails.getEmail() != null) {
                user.setEmail(userDetails.getEmail());
            }
            if (userDetails.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            }
            if (userDetails.getRole() != null) {
                user.setRole(userDetails.getRole());
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    @Override
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}