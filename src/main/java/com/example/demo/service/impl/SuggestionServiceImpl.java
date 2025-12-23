package com.example.demo.service.impl;

import com.example.demo.entity.Suggestion;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    private final FarmRepository farmRepository;
    private final SuggestionRepository suggestionRepository;

    @Autowired
    public SuggestionServiceImpl(FarmRepository farmRepository,
                                 SuggestionRepository suggestionRepository) {
        this.farmRepository = farmRepository;
        this.suggestionRepository = suggestionRepository;
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {
        // You can use farmRepository to validate farmId if needed
        return suggestionRepository.save(new Suggestion());
    }

    @Override
    public Suggestion getSuggestion(Long suggestionId) {
        return suggestionRepository.findById(suggestionId)
                .orElseThrow(() -> new ResourceNotFoundException("Suggestion not found"));
    }
}
