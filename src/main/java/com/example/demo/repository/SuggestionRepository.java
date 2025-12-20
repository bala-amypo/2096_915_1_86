package com.example.demo.repository;

import com.example.demo.entity.Suggestion;
import java.util.List;

public interface SuggestionRepository {
    List<Suggestion> findByFarmId(Long farmId);
}
