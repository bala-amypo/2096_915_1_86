package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.CatalogService;
import com.example.demo.service.FarmService;
import com.example.demo.service.SuggestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SuggestionServiceImpl implements SuggestionService {
    private final FarmService farmService;
    private final CatalogService catalogService;
    private final SuggestionRepository repo;

    public SuggestionServiceImpl(FarmService farmService, CatalogService catalogService, SuggestionRepository repo) {
        this.farmService = farmService;
        this.catalogService = catalogService;
        this.repo = repo;
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {
        Farm farm = farmService.getFarmById(farmId);
        List<Crop> crops = catalogService.findSuitableCrops(farm.getSoilPH(), farm.getWaterLevel(), farm.getSeason());
        List<String> cropNames = crops.stream().map(Crop::getName).collect(Collectors.toList());
        List<Fertilizer> ferts = catalogService.findFertilizersForCrops(cropNames);

        Suggestion s = Suggestion.builder()
                .farm(farm)
                .suggestedCrops(String.join(",", cropNames))
                .suggestedFertilizers(ferts.stream().map(Fertilizer::getName).collect(Collectors.joining(",")))
                .build();
        return repo.save(s);
    }

    @Override
    public Suggestion getSuggestion(Long suggestionId) {
        return repo.findById(suggestionId).orElseThrow(() -> new RuntimeException("Suggestion not found"));
    }

    @Override
    public List<Suggestion> getSuggestionsByFarm(Long farmId) {
        return repo.findByFarmId(farmId);
    }
}
