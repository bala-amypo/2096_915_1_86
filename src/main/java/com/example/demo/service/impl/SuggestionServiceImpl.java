package com.example.demo.service.impl;

import com.example.demo.entity.Suggestion;
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
    private final SuggestionRepository suggestionRepository;

    public SuggestionServiceImpl(FarmService farmService,
                                 CatalogService catalogService,
                                 SuggestionRepository suggestionRepository) {
        this.farmService = farmService;
        this.catalogService = catalogService;
        this.suggestionRepository = suggestionRepository;
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {
        var farm = farmService.getFarmById(farmId);

        var crops = catalogService.findSuitableCrops(
                farm.getSoilPH(),
                farm.getWaterLevel(),
                farm.getSeason()
        );

        var cropNames = crops.stream()
                .map(c -> c.getName())
                .collect(Collectors.toList());

        var fertilizers = catalogService.findFertilizersForCrops(cropNames);

        Suggestion s = Suggestion.builder()
                .farm(farm)
                .suggestedCrops(String.join(",", cropNames))
                .suggestedFertilizers(
                        fertilizers.stream()
                                .map(f -> f.getName())
                                .collect(Collectors.joining(","))
                )
                .build();

        return suggestionRepository.save(s);
    }

    @Override
    public Suggestion getSuggestion(Long id) {
        return suggestionRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Suggestion> getSuggestionsByFarm(Long farmId) {
        return suggestionRepository.findByFarmId(farmId);
    }
}
