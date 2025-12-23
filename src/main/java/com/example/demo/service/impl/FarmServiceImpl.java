package com.example.demo.service.impl;

import com.example.demo.entity.Farm;
import com.example.demo.repository.FarmRepository;
import com.example.demo.service.FarmService; 
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmServiceImpl implements FarmService {

    private final UserService userService;
    private final FarmRepository farmRepository;

    // Constructor that matches tests expecting UserService
    public FarmServiceImpl(UserService userService, FarmRepository farmRepository) {
        this.userService = userService;
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
