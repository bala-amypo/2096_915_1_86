package com.example.demo.service;

import com.example.demo.entity.Suggestion;
import java.util.List;

public interface SuggestionService {
    Suggestion generateSuggestion(Long farmId);
    Suggestion getSuggestion(Long id);
    List<Suggestion> getSuggestionsByFarm(Long farmId);
}
package com.example.demo.service;

import com.example.demo.entity.*;
import java.util.List;

public interface CatalogService {
    Crop addCrop(Crop crop);
    Fertilizer addFertilizer(Fertilizer fertilizer);
    List<Crop> findSuitableCrops(Double ph, Double water, String season);
    List<Fertilizer> findFertilizersForCrops(List<String> crops);
}
