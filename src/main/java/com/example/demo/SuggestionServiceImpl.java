package com.example.demo.service;

import com.example.demo.entity.Farm;
import com.example.demo.entity.Suggestion;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    private final SuggestionRepository suggestionRepository;
    private final FarmRepository farmRepository;

    @Autowired
    public SuggestionServiceImpl(SuggestionRepository suggestionRepository, FarmRepository farmRepository) {
        this.suggestionRepository = suggestionRepository;
        this.farmRepository = farmRepository;
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {
        Farm farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new IllegalArgumentException("Farm not found with id: " + farmId));

        Suggestion suggestion = Suggestion.builder()
                .farm(farm)
                .suggestedCrops("Try rotating crops for better soil health")
                .suggestedFertilizers("Use organic fertilizer for sustainable yield")
                .build();

        return suggestionRepository.save(suggestion);
    }

    @Override
    public Suggestion getSuggestion(Long suggestionId) {
        return suggestionRepository.findById(suggestionId)
                .orElseThrow(() -> new IllegalArgumentException("Suggestion not found with id: " + suggestionId));
    }

    @Override
    public List<Suggestion> getSuggestionsByFarm(Long farmId) {
        return suggestionRepository.findByFarm_Id(farmId);
    }
}
