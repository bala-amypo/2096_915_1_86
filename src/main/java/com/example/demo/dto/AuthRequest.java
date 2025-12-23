package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor // ensures deserialization works
public class AuthRequest {
    private String email;
    private String password;

    // Added constructor to satisfy tests
    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
