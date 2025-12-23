package com.example.demo.service;

import com.example.demo.entity.Farm;
import com.example.demo.repository.FarmRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;

    public FarmServiceImpl(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Override
    public Farm createFarm(Farm farm, String ownerEmail) {
        farm.setOwner(ownerEmail);
        return farmRepository.save(farm);
    }

    @Override
    public List<Farm> getFarmsByOwner(String ownerEmail) {
        return farmRepository.findByOwner(ownerEmail);
    }

    @Override
    public Farm getFarmById(Long id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found"));
    }
}
