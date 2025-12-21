package com.example.demo.controller;

import com.example.demo.entity.Farm;
import com.example.demo.entity.User;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/farms")
public class FarmController {

    private final FarmRepository farmRepository;
    private final UserRepository userRepository;

    public FarmController(FarmRepository farmRepository, UserRepository userRepository) {
        this.farmRepository = farmRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public Farm createFarm(@RequestBody Farm farm, Authentication auth) {
        String username = auth.getName(); // e.g. "admin"
        User owner = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        farm.setOwner(owner);
        return farmRepository.save(farm);
    }
}
