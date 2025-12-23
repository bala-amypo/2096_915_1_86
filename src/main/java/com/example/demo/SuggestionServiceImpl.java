package com.example.demo;

import com.example.demo.entity.Farm;
import com.example.demo.entity.Suggestion;
import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.FarmService;
import com.example.demo.service.CatalogService;
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
        Farm farm = farmService.getFarmById(farmId);
        if (farm == null) {
            throw new ResourceNotFoundException("Farm not found");
        }

        List<Crop> crops = catalogService.findSuitableCrops(
                farm.getSoilPH(),
                farm.getWaterLevel(),
                farm.getSeason()
        );

        List<Fertilizer> fertilizers = catalogService.findFertilizersForCrops(
                crops.stream().map(Crop::getName).collect(Collectors.toList())
        );

        Suggestion suggestion = Suggestion.builder()
                .farm(farm)
                .suggestedCrops(crops.stream().map(Crop::getName).collect(Collectors.joining(",")))
                .suggestedFertilizers(fertilizers.stream().map(Fertilizer::getName).collect(Collectors.joining(",")))
                .build();

        return suggestionRepository.save(suggestion);
    }

    @Override
    public Suggestion getSuggestion(Long id) {
        return suggestionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suggestion not found"));
    }
}
