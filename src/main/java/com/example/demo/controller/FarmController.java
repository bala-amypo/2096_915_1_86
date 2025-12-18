package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    @Autowired
    private FarmService farmService;

    // CREATE FARM
    @PostMapping("/add/{userId}")
    public ResponseEntity<Farm> addFarm(
            @RequestBody Farm farm,
            @PathVariable Long userId) {

        Farm savedFarm = farmService.addFarm(farm, userId);
        return ResponseEntity.ok(savedFarm);
    }

    // GET FARM BY ID
    @GetMapping("/{farmId}")
    public ResponseEntity<Farm> getFarmById(@PathVariable Long farmId) {

        Farm farm = farmService.getFarmById(farmId);
        return ResponseEntity.ok(farm);
    }

    // GET FARMS BY USER / OWNER
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Farm>> getFarmsByOwner(@PathVariable Long ownerId) {

        List<Farm> farms = farmService.getFarmsByOwner(ownerId);
        return ResponseEntity.ok(farms);
    }
}
