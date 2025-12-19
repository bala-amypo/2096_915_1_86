package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public ResponseEntity<AuthResponse> register(RegisterRequest req) {
        User u = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(req.getPassword())
                .role("USER")
                .build();
        User saved = userService.register(u);
        return ResponseEntity.ok(new AuthResponse("registered"));
    }

    public ResponseEntity<AuthResponse> login(AuthRequest req) {
        User found = userService.findByEmail(req.getEmail());
        if (found == null) {
            return ResponseEntity.status(401).build();
        }
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        if (!enc.matches(req.getPassword(), found.getPassword())) {
            return ResponseEntity.status(401).build();
        }
        String token = jwtTokenProvider.createToken(found.getId(), found.getEmail(), found.getRole());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
