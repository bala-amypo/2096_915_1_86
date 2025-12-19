package com.example.demo.service.impl;

import com.example.demo.entity.Farm;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FarmService;

import java.util.List;

public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepo;
    private final UserRepository userRepo;

    public FarmServiceImpl(FarmRepository farmRepo, UserRepository userRepo) {
        this.farmRepo = farmRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Farm createFarm(Farm farm, Long ownerId) {
        if (farm.getSoilPH() < 3 || farm.getSoilPH() > 10) {
            throw new IllegalArgumentException("pH out of range");
        }
        farm.setOwner(userRepo.findById(ownerId).orElseThrow());
        return farmRepo.save(farm);
    }

    @Override
    public Farm getFarmById(Long id) {
        return farmRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Farm not found"));
    }

    
    @Override
    public List<Farm> getFarmsByOwner(Long ownerId) {
        return farmRepo.findByOwnerId(ownerId);
    }
}
