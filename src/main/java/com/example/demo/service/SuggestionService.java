package com.example.demo.service;

import com.example.demo.entity.Suggestion;

public interface SuggestionService {

    Suggestion generate(Long farmId);

    Suggestion getSuggestion(Long id);
}
