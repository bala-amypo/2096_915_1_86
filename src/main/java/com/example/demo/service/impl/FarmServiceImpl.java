package com.example.demo.service.impl;

import com.example.demo.entity.Farm;
import com.example.demo.entity.User;
import com.example.demo.repository.FarmRepository;
import com.example.demo.service.FarmService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmServiceImpl implements FarmService {

    private final UserService userService;
    private final FarmRepository farmRepository;

    // Full constructor
    public FarmServiceImpl(UserService userService, FarmRepository farmRepository) {
        this.userService = userService;
        this.farmRepository = farmRepository;
    }

    // Overloaded constructor for tests that only pass UserService
    public FarmServiceImpl(UserService userService) {
        this.userService = userService;
        this.farmRepository = null;
    }

    @Override
    public Farm createFarm(Farm farm, String ownerEmail) {
        User owner = userService.findByEmail(ownerEmail);
        farm.setOwner(owner);
        return farmRepository.save(farm);
    }

    @Override
    public List<Farm> getFarmsByOwner(String ownerEmail) {
        User owner = userService.findByEmail(ownerEmail);
        return farmRepository.findByOwner(owner);
    }

    @Override
    public Farm getFarmById(Long id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found"));
    }
}
