package com.example.demo;

import com.example.demo.entity.Farm;
import com.example.demo.entity.Suggestion;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.CatalogService;
import com.example.demo.service.SuggestionService;

public class SuggestionServiceImpl implements SuggestionService {

    private final FarmRepository farmRepository;
    private final CatalogService catalogService;
    private final SuggestionRepository suggestionRepository;

    public SuggestionServiceImpl(FarmRepository farmRepository,
                                 CatalogService catalogService,
                                 SuggestionRepository suggestionRepository) {
        this.farmRepository = farmRepository;
        this.catalogService = catalogService;
        this.suggestionRepository = suggestionRepository;
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {

        Farm farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new RuntimeException("Farm not found"));

        Suggestion suggestion = new Suggestion();
        suggestion.setFarm(farm);

        return suggestionRepository.save(suggestion);
    }

    @Override
    public Suggestion getSuggestion(Long id) {
        return suggestionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suggestion not found"));
    }
}
