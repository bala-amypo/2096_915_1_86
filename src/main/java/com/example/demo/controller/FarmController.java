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

    @PostMapping
    public Farm createFarm(@RequestBody Farm farm, Authentication auth) {
        return farmService.createFarm(farm, auth.getName());
    }

    @GetMapping
    public List<Farm> myFarms(Authentication auth) {
        return farmService.getFarmsByOwner(auth.getName());
    }

    @GetMapping("/{id}")
    public Farm getFarm(@PathVariable Long id) {
        return farmService.getFarmById(id);
    }
}
