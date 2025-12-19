package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface UserService {
    User register(User user);
    User findByEmail(String email);
    boolean validatePassword(String rawPassword, String encodedPassword);
    User findById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
}


├── AuthServiceImpl.java           (implementation)
    ├── CatalogServiceImpl.java        (implementation)
    ├── InteractionServiceImpl.java    (implementation)
    ├── RuleServiceImpl.java           (implementation)
    └── UserServiceImpl.java