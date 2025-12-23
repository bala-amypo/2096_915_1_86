package com.example.demo.controller;

import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import com.example.demo.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farms")
public class FarmController {

    private final FarmService farmService;
    private final UserService userService;

    public FarmController(FarmService farmService, UserService userService) {
        this.farmService = farmService;
        this.userService = userService;
    }

    @PostMapping
    public Farm createFarm(@RequestBody Farm farm, Authentication auth) {
        return farmService.createFarm(farm, auth.getName());
    }

    @GetMapping
    public List<Farm> listFarms(Authentication auth) {
        return farmService.getFarmsByOwner(auth.getName());
    }

    @GetMapping("/{id}")
    public Farm getFarm(@PathVariable Long id) {
        return farmService.getFarmById(id);
    }
}
