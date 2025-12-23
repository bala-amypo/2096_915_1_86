package com.example.demo.service;

import com.example.demo.entity.Farm;
import com.example.demo.repository.FarmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;

    public FarmServiceImpl(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Override
    public Farm createFarm(Farm farm, Long ownerId) {
        // ✅ Validate soil pH
        if (farm.getSoilPH() < 0 || farm.getSoilPH() > 14) {
            throw new IllegalArgumentException("Invalid pH value: " + farm.getSoilPH());
        }

        // ✅ Set ownerId
        farm.setOwnerId(ownerId);

        return farmRepository.save(farm);
    }

    @Override
    public List<Farm> getFarmsByOwner(Long ownerId) {
        return farmRepository.findByOwnerId(ownerId);
    }

    @Override
    public Farm getFarmById(Long id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Farm not found with id " + id));
    }
}
