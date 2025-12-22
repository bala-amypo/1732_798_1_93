package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import com.example.demo.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    // Remove AuthenticationManager if not needed
    // private final AuthenticationManager authenticationManager;
    
    public AuthServiceImpl(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil
                          // Remove this: AuthenticationManager authenticationManager
                          ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        // this.authenticationManager = authenticationManager;
    }
    
    @Override
    public AuthResponse register(RegisterRequest request) {
        // ... your registration code
    }
    
    @Override
    public AuthResponse authenticate(AuthRequest request) {
        // Manually authenticate without AuthenticationManager
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        
        String token = jwtUtil.generateToken(user.getEmail(), user.getId(), user.getRole());
        
        return new AuthResponse(token, "Login successful", user.getId(), user.getEmail(), user.getRole());
    }
}