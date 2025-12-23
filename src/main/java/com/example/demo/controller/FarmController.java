package com.example.demo.controller;

import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farms")
public class FarmController {

    private final FarmService farmService;

    
    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    // Create a new farm
    @PostMapping
    public Farm createFarm(@RequestBody Farm farm, Authentication authentication) {
        return farmService.createFarm(farm, authentication.getName());
    }

    // Get all farms for current user
    @GetMapping
    public List<Farm> getFarms(Authentication authentication) {
        return farmService.getFarmsByOwner(authentication.getName());
    }

    // Get farm by ID
    @GetMapping("/{id}")
    public Farm getFarmById(@PathVariable Long id) {
        return farmService.getFarmById(id);
    }

    // Alias method REQUIRED by tests
    public List<Farm> listFarms(Authentication authentication) {
        return getFarms(authentication);
    }
}
