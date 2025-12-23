package com.example.demo.controller;

import com.example.demo.entity.Farm;
import com.example.demo.dto.FarmRequest;
import com.example.demo.service.FarmService;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Farm> createFarm(@RequestBody FarmRequest request,
                                           Authentication authentication) {
        Long ownerId = (Long) authentication.getPrincipal();
        Farm farm = Farm.builder()
                .name(request.getName())
                .soilPH(request.getSoilPH())
                .waterLevel(request.getWaterLevel())
                .season(request.getSeason())
                .build();

        Farm saved = farmService.createFarm(farm, ownerId);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Farm>> listFarms(Authentication authentication) {
        Long ownerId = (Long) authentication.getPrincipal();
        List<Farm> farms = farmService.getFarmsByOwner(ownerId);
        return ResponseEntity.ok(farms);
    }
}
