package com.example.demo.service;

import com.example.demo.entity.Farm;
import java.util.List;

public interface FarmService {
    Farm createFarm(Farm farm, String ownerEmail);
    List<Farm> getFarmsByOwner(String ownerEmail);
    Farm getFarmById(Long id);
}
