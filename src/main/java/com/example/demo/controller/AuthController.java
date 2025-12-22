package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    
    public AuthController(AuthService authService, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            // Use the correct constructor
            User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()  // This can be null, User constructor will handle it
            );
            
            User savedUser = authService.registerUser(user);
            
            return ResponseEntity.ok(Map.of(
                "message", "User registered successfully",
                "email", savedUser.getEmail(),
                "id", savedUser.getId(),
                "role", savedUser.getRole()
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            User user = authService.findByEmail(request.getEmail());
            
            // Check password
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "email", user.getEmail(),
                    "role", user.getRole(),
                    "id", user.getId()
                ));
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid password"));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid email or password"));
        }
    }
}