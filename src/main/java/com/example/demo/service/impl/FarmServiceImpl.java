package com.example.demo;

import com.example.demo.entity.Farm;
import com.example.demo.entity.User;
import com.example.demo.repository.FarmRepository;
import com.example.demo.service.FarmService;
import com.example.demo.service.UserService;

import java.util.List;

public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private final UserService userService;

    public FarmServiceImpl(FarmRepository farmRepository,
                           UserService userService) {
        this.farmRepository = farmRepository;
        this.userService = userService;
    }

    @Override
    public Farm createFarm(Farm farm, String email) {
        User owner = userService.findByEmail(email);
        farm.setOwner(owner);
        return farmRepository.save(farm);
    }

    @Override
    public List<Farm> getFarmsByOwner(String email) {
        User owner = userService.findByEmail(email);
        return farmRepository.findByOwner(owner);
    }

    @Override
    public Farm getFarmById(Long id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found"));
    }
}
