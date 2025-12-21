package com.example.demo.service;

import com.example.demo.entity.Farm;
import java.util.List;

public interface FarmService {

    // Create a farm for the logged-in user
    Farm createFarm(Farm farm, String username);

    // Get all farms owned by the logged-in user
    List<Farm> getFarmsByOwner(String username);

    // Get a farm by its ID
    Farm getFarmById(Long farmId);
}
