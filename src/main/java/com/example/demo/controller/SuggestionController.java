package com.example.demo.controller;

import com.example.demo.entity.Suggestion;
import com.example.demo.service.SuggestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suggestions")
@Tag(name = "Suggestion Management", description = "Endpoints for managing suggestions")
public class SuggestionController {

    private final SuggestionService suggestionService;

    @Autowired
    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @Operation(summary = "Generate a suggestion", description = "Generates a suggestion for a given farm")
    @PostMapping("/{farmId}")
    public Suggestion generateSuggestion(@PathVariable Long farmId) {
        return suggestionService.generateSuggestion(farmId);
    }

    @Operation(summary = "Get suggestion by ID", description = "Fetches a suggestion by its unique ID")
    @GetMapping("/{id}")
    public Suggestion getSuggestion(@PathVariable Long id) {
        return suggestionService.getSuggestion(id);
    }
}
