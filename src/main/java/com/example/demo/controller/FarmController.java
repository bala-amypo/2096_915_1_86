package com.example.demo.controller;

import com.example.demo.dto.FarmRequest;
import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Farm> createFarm(@RequestBody FarmRequest req,
                                           @RequestParam Long userId) {

        Farm farm = Farm.builder()
                .name(req.getName())
                .soilPH(req.getSoilPH())
                .waterLevel(req.getWaterLevel())
                .season(req.getSeason())
                .build();

        Farm saved = farmService.createFarm(farm, userId);

        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Farm>> listFarms(@RequestParam Long userId) {
        return ResponseEntity.ok(farmService.getFarmsByOwner(userId));
    }

    @GetMapping("/{farmId}")
    public ResponseEntity<Farm> getFarm(@PathVariable Long farmId) {
        return ResponseEntity.ok(farmService.getFarmById(farmId));
    }
}
