package com.example.demo.service;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import java.util.List;

public interface CatalogService {
    Crop addCrop(Crop crop);
    Fertilizer addFertilizer(Fertilizer fertilizer);

    // Signature must match tests: ph, waterLevel, season
    List<Crop> findSuitableCrops(double ph, double waterLevel, String season);

    List<Fertilizer> findFertilizersForCrops(List<String> crops);
}
