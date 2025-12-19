package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.CatalogService;
import com.example.demo.service.FarmService;
import com.example.demo.service.SuggestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    @Autowired
    private FarmService farmService;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private SuggestionRepository suggestionRepository;

    @Override
    public Suggestion generateSuggestion(Long farmId) {

        Farm farm = farmService.getFarmById(farmId);

        List<Crop> crops = catalogService.findSuitableCrops(
                farm.getSoilPH(),
                farm.getSeason()
        );

        List<String> cropNames = crops.stream()
                .map(Crop::getName)
                .collect(Collectors.toList());

        List<Fertilizer> ferts = catalogService.findFertilizersForCrops(cropNames);

        Suggestion suggestion = new Suggestion();
        suggestion.setFarm(farm);
        suggestion.setSuggestedCrops(String.join(",", cropNames));
        suggestion.setSuggestedFertilizers(
                ferts.stream().map(Fertilizer::getName).collect(Collectors.joining(","))
        );

        return suggestionRepository.save(suggestion);
    }

    @Override
    public Suggestion getSuggestion(Long suggestionId) {

        return suggestionRepository.findById(suggestionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Suggestion not found with id " + suggestionId));
    }
}
