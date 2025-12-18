package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication endpoints")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    
    public AuthController(AuthenticationManager authenticationManager,
                         JwtUtil jwtUtil,
                         UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }
    
    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            if (request.getRole() != null) {
                user.setRole(request.getRole());
            }
            
            User registeredUser = userService.register(user);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("userId", registeredUser.getId());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/login")
    @Operation(summary = "Login user and get JWT token")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails.getUsername(), 
                                               userDetails.getUserId(), 
                                               userDetails.getRole());
            
            return ResponseEntity.ok(new AuthResponse(token, 
                                                    userDetails.getUserId(),
                                                    userDetails.getUsername(),
                                                    userDetails.getRole()));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }
}