package com.example.demo.service;

import com.example.demo.entity.Suggestion;

import java.util.List;

public interface SuggestionService {
    Suggestion generateForFarm(Long farmId);
    List<Suggestion> listForFarm(Long farmId);
}
