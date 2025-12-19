package com.example.demo.controller;

import com.example.demo.dto.CropRequest;
import com.example.demo.dto.FertilizerRequest;
import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class CatalogController {
    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public ResponseEntity<Crop> addCrop(CropRequest req, Authentication auth) {
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            return ResponseEntity.status(403).build();
        }
        Crop c = Crop.builder()
                .name(req.getName())
                .suitablePHMin(req.getSuitablePHMin())
                .suitablePHMax(req.getSuitablePHMax())
                .requiredWater(req.getRequiredWater())
                .season(req.getSeason())
                .build();
        Crop saved = catalogService.addCrop(c);
        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<Fertilizer> addFertilizer(FertilizerRequest req, Authentication auth) {
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            return ResponseEntity.status(403).build();
        }
        Fertilizer f = Fertilizer.builder()
                .name(req.getName())
                .npkRatio(req.getNpkRatio())
                .recommendedForCrops(req.getRecommendedForCrops())
                .build();
        Fertilizer saved = catalogService.addFertilizer(f);
        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<List<Crop>> findCrops(double ph, double water, String season) {
        return ResponseEntity.ok(catalogService.findSuitableCrops(ph, water, season));
    }

    public ResponseEntity<List<Fertilizer>> findFerts(String crop) {
        return ResponseEntity.ok(catalogService.findFertilizersForCrops(List.of(crop)));
    }
}
