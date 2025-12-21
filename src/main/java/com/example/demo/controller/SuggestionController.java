package com.example.demo.controller;

import com.example.demo.entity.Suggestion;
import com.example.demo.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;   

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    @PostMapping("/{farmId}")
    public Suggestion generate(@PathVariable Long farmId) {
        return suggestionService.generateSuggestion(farmId);
    }

    @GetMapping("/{suggestionId}")
    public Suggestion getById(@PathVariable Long suggestionId) {
        return suggestionService.getSuggestion(suggestionId);
    }

    @GetMapping("/farm/{farmId}")
    public List<Suggestion> getByFarm(@PathVariable Long farmId) {
        return suggestionService.getSuggestionsByFarm(farmId);
    }
}
