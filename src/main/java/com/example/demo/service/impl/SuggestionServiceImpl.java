package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.CatalogService;
import com.example.demo.service.FarmService;
import com.example.demo.service.SuggestionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuggestionServiceImpl implements SuggestionService {
    private final FarmService farmService;
    private final CatalogService catalogService;
    private final SuggestionRepository suggestionRepo;

    // Constructor required by tests
    public SuggestionServiceImpl(FarmService farmService,
                                 CatalogService catalogService,
                                 SuggestionRepository suggestionRepo) {
        this.farmService = farmService;
        this.catalogService = catalogService;
        this.suggestionRepo = suggestionRepo;
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {
        Farm farm = farmService.getFarmById(farmId);
        List<Crop> crops = catalogService.findSuitableCrops(
                farm.getSoilPH(), farm.getWaterLevel(), farm.getSeason());
        List<String> cropNames = crops.stream().map(Crop::getName).collect(Collectors.toList());

        List<Fertilizer> fertilizers = catalogService.findFertilizersForCrops(cropNames);
        String cropCsv = String.join(",", cropNames);
        String fertCsv = fertilizers.stream().map(Fertilizer::getName).collect(Collectors.joining(","));

        Suggestion suggestion = Suggestion.builder()
                .farm(farm)
                .suggestedCrops(cropCsv)
                .suggestedFertilizers(fertCsv)
                .build();

        return suggestionRepo.save(suggestion);
    }

    @Override
    public Suggestion getSuggestion(Long suggestionId) {
        return suggestionRepo.findById(suggestionId)
                .orElseThrow(() -> new RuntimeException("Suggestion not found"));
    }

    @Override
    public List<Suggestion> getSuggestionsByFarm(Long farmId) {
        return suggestionRepo.findByFarmId(farmId);
    }
}
