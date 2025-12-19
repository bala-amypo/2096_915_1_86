package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        // encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        User found = userService.findByEmail(email);
        if (found != null && passwordEncoder.matches(password, found.getPassword())) {
            return "Login successful for " + found.getEmail();
        }
        return "Invalid credentials";
    }
}
