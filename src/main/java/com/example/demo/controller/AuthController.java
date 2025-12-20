package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.security.JwtTokenProvider;

public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }
}
