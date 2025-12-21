package com.example.demo;

import com.example.demo.entity.Farm;
import com.example.demo.entity.Suggestion;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.SuggestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SuggestionServiceImpl implements SuggestionService {

    private final FarmRepository farmRepository;
    private final SuggestionRepository suggestionRepository;

    public SuggestionServiceImpl(FarmRepository farmRepository, SuggestionRepository suggestionRepository) {
        this.farmRepository = farmRepository;
        this.suggestionRepository = suggestionRepository;
    }

    @Override
    public Suggestion generateForFarm(Long farmId) {
        Farm farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new ResourceNotFoundException("Farm not found"));

        // Simple rule-based suggestion; adapt as needed
        String crops = suggestCrops(farm.getSoilPH(), farm.getSeason());
        String fertilizers = suggestFertilizers(farm.getSoilPH());

        Suggestion s = Suggestion.builder()
                .farm(farm)
                .suggestedCrops(crops)
                .suggestedFertilizers(fertilizers)
                .build();
        return suggestionRepository.save(s);
    }

    @Override
    public List<Suggestion> listForFarm(Long farmId) {
        return suggestionRepository.findByFarmId(farmId);
    }

    private String suggestCrops(Double ph, String season) {
        if (season.equals("Kharif")) return "Rice, Maize";
        if (season.equals("Rabi")) return "Wheat, Mustard";
        return "Pulses, Vegetables";
    }

    private String suggestFertilizers(Double ph) {
        if (ph < 6.0) return "Lime, NPK 10-26-26";
        if (ph <= 7.5) return "Balanced NPK 20-20-0";
        return "Gypsum, NPK 12-32-16";
    }
}
