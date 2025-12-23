package com.example.demo.controller;

import com.example.demo.entity.Suggestion;
import com.example.demo.service.SuggestionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {

    private final SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @PostMapping("/{farmId}")
    public Suggestion generate(@PathVariable Long farmId) {
        return suggestionService.generate(farmId);
    }

    @GetMapping("/{id}")
    public Suggestion getSuggestion(@PathVariable Long id) {
        return suggestionService.getSuggestion(id);
    }
}
