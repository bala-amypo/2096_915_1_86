package com.example.demo.service;

import com.example.demo.entity.Suggestion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    @Override
    public Suggestion generateSuggestion(Long farmId) {
        return Suggestion.builder()
                .id(1L)
                .farmId(farmId)
                .content("Use organic fertilizer for better yield.")
                .build();
    }

    @Override
    public Suggestion getSuggestion(Long suggestionId) {
        return Suggestion.builder()
                .id(suggestionId)
                .farmId(1L)
                .content("Rotate crops to improve soil health.")
                .build();
    }

    @Override
    public List<Suggestion> getSuggestionsByFarm(Long farmId) {
        List<Suggestion> list = new ArrayList<>();
        list.add(generateSuggestion(farmId));
        return list;
    }
}
