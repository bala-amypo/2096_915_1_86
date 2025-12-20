package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.*;

import java.util.List;
import java.util.stream.Collectors;

public class SuggestionServiceImpl implements SuggestionService {

    private final FarmService farmService;
    private final CatalogService catalogService;
    private final SuggestionRepository repo;

    public SuggestionServiceImpl(FarmService farmService,
                                 CatalogService catalogService,
                                 SuggestionRepository repo) {
        this.farmService = farmService;
        this.catalogService = catalogService;
        this.repo = repo;
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {
        Farm farm = farmService.getFarmById(farmId);

        List<Crop> crops = catalogService.findSuitableCrops(
                farm.getSoilPH(),
                farm.getWaterLevel(),
                farm.getSeason());

        List<Fertilizer> ferts =
                catalogService.findFertilizersForCrops(
                        crops.stream()
                                .map(Crop::getName)
                                .collect(Collectors.toList()));

        Suggestion s = new Suggestion();
        s.setFarm(farm);
        s.setSuggestedCrops(
                crops.stream()
                        .map(Crop::getName)
                        .collect(Collectors.joining(",")));
        s.setSuggestedFertilizers(
                ferts.stream()
                        .map(Fertilizer::getName)
                        .collect(Collectors.joining(",")));

        return repo.save(s);
    }

    @Override
    public List<Suggestion> getSuggestionsByFarm(Long farmId) {
        return repo.findByFarmId(farmId);
    }

    @Override
    public Suggestion getSuggestion(Long id) {
        return repo.findById(id).orElseThrow();
    }
}
