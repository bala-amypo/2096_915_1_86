package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService,
                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        // mocked in tests
        User user = userService.findByEmail(request.getUsername());

        // required for t34 (wrong password)
        if (!userService.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        // mocked in t33 → returns "token123"
        String token = jwtTokenProvider.createToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(
                new AuthResponse(token, user.getUsername())
        );
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .name(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .role("USER")
                .build();

        User savedUser = userService.register(user);

        String token = jwtTokenProvider.createToken(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole()
        );

        return ResponseEntity.ok(
                new AuthResponse(token, savedUser.getUsername())
        );
    }
}
