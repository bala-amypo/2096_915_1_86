package com.example.demo.controller;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.service.CatalogService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    private CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    // ADD CROP
    @PostMapping("/crop")
    public ResponseEntity<Crop> addCrop(@RequestBody Crop crop) {
        Crop saved = catalogService.addCrop(crop);
        return ResponseEntity.ok(saved);
    }

    // ADD FERTILIZER
    @PostMapping("/fertilizer")
    public ResponseEntity<Fertilizer> addFertilizer(@RequestBody Fertilizer fertilizer) {
        Fertilizer saved = catalogService.addFertilizer(fertilizer);
        return ResponseEntity.ok(saved);
    }

    // GET SUITABLE CROPS
    @GetMapping("/crops")
    public ResponseEntity<List<Crop>> getSuitableCrops(
            @RequestParam double ph,
            @RequestParam String season) {

        List<Crop> crops = catalogService.findSuitableCrops(ph, season);
        return ResponseEntity.ok(crops);
    }

    // GET SUITABLE FERTILIZERS
    @PostMapping("/fertilizers")
    public ResponseEntity<List<Fertilizer>> getFertilizers(@RequestBody List<String> cropNames) {

        List<Fertilizer> fertilizers = catalogService.findFertilizersForCrops(cropNames);
        return ResponseEntity.ok(fertilizers);
    }
}
