package com.example.demo.controller;

import com.example.demo.entity.Suggestion;
import com.example.demo.service.SuggestionService;
import org.springframework.http.ResponseEntity;

public class SuggestionController {
    private final SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    public ResponseEntity<Suggestion> generate(Long farmId) {
        Suggestion s = suggestionService.generateSuggestion(farmId);
        return ResponseEntity.ok(s);
    }

    public ResponseEntity<Suggestion> getSuggestion(Long id) {
        Suggestion s = suggestionService.getSuggestion(id);
        return ResponseEntity.ok(s);
    }
}
