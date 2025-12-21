package com.example.demo.service;

import com.example.demo.entity.Farm;

import java.util.List;

public interface FarmService {
    Farm createFarm(Farm farm, String username);
    List<Farm> getFarmsByOwner(String username);
    Farm getFarmById(Long farmId);
}
