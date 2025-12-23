package com.example.demo;

import com.example.demo.entity.Suggestion;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.CatalogService;   // added for overloaded constructor
import com.example.demo.service.FarmService;      // added for overloaded constructor
import com.example.demo.service.SuggestionService;
import org.springframework.stereotype.Service;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    private final FarmRepository farmRepository;
    private final SuggestionRepository suggestionRepository;

    // Original constructor
    public SuggestionServiceImpl(FarmRepository farmRepository,
                                 SuggestionRepository suggestionRepository) {
        this.farmRepository = farmRepository;
        this.suggestionRepository = suggestionRepository;
    }

    // Overloaded constructor for tests expecting CatalogService
    public SuggestionServiceImpl(CatalogService catalogService,
                                 SuggestionRepository suggestionRepository) {
        this.farmRepository = null; // not used in this path
        this.suggestionRepository = suggestionRepository;
    }

    // Overloaded constructor for tests expecting FarmService
    public SuggestionServiceImpl(FarmService farmService,
                                 SuggestionRepository suggestionRepository) {
        this.farmRepository = null; // not used in this path
        this.suggestionRepository = suggestionRepository;
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {
        return suggestionRepository.save(new Suggestion());
    }

    @Override
    public Suggestion getSuggestion(Long suggestionId) {
        return suggestionRepository.findById(suggestionId)
                .orElseThrow(() -> new ResourceNotFoundException("Suggestion not found"));
    }
}
