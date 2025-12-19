package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    public User findByUsername(String username) {
        return repo.findByUsername(username).orElse(null);
    }
}
