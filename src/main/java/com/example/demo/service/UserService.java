package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    User register(User user);

    User findByEmail(String email);

    boolean matches(String rawPassword, String encodedPassword);

    User findById(Long id);

    // ✅ Added to satisfy AuthController
    User authenticate(String username, String password);
}
