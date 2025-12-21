package com.example.demo.controller;

import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farms")
@Tag(name = "Farm Management", description = "Endpoints for managing farms")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @Operation(summary = "Create a new farm", description = "Creates a farm for the authenticated user")
    @PostMapping
    public Farm createFarm(@RequestBody Farm farm, Authentication auth) {
        return farmService.createFarm(farm, auth.getName());
    }

    @Operation(summary = "Get all farms for current user", description = "Lists farms owned by the authenticated user")
    @GetMapping
    public List<Farm> myFarms(Authentication auth) {
        return farmService.getFarmsByOwner(auth.getName());
    }

    @Operation(summary = "Get farm by ID", description = "Fetches a farm by its unique ID")
    @GetMapping("/{id}")
    public Farm getFarm(@PathVariable Long id) {
        return farmService.getFarmById(id);
    }
}
