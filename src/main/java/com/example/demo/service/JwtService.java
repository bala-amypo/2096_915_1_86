package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    // For simplicity, just return a dummy token string.
    // In a real app, you would generate a signed JWT.
    public String generateToken(User user) {
        return "token-for-" + user.getUsername();
    }
}
