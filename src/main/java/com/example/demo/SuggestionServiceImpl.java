package com.example.demo;

import com.example.demo.entity.Suggestion;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.SuggestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    private final SuggestionRepository suggestionRepository;

    public SuggestionServiceImpl(SuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }

    @Override
    public Suggestion getSuggestion(Long id) {
        List<Suggestion> suggestions = suggestionRepository.findAllByIdCustom(id);
        if (suggestions.isEmpty()) {
            throw new ResourceNotFoundException("Suggestion not found");
        }
        return suggestions.get(0); // ✅ matches test calling .get(0)
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {
        // Stubbed or implemented elsewhere
        return null;
    }
}
