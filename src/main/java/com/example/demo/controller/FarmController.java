package com.example.demo.controller;

import com.example.demo.dto.FarmRequest;
import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public class FarmController {
    private final FarmService farmService;
    private final UserService userService;

    public FarmController(FarmService farmService, UserService userService) {
        this.farmService = farmService;
        this.userService = userService;
    }

    public ResponseEntity<Farm> createFarm(FarmRequest req, Authentication auth) {
        Long ownerId = (Long) auth.getPrincipal();
        Farm f = Farm.builder()
                .name(req.getName())
                .soilPH(req.getSoilPH())
                .waterLevel(req.getWaterLevel())
                .season(req.getSeason())
                .build();
        Farm saved = farmService.createFarm(f, ownerId);
        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<List<Farm>> listFarms(Authentication auth) {
        Long ownerId = (Long) auth.getPrincipal();
        List<Farm> farms = farmService.getFarmsByOwner(ownerId);
        return ResponseEntity.ok(farms);
    }
}
