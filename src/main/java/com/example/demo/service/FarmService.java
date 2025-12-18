package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Farm;

public interface FarmService {

    Farm addFarm(Farm farm, Long userId);

    Farm getFarmById(Long id);

    List<Farm> getFarmsByOwner(Long ownerId);
}
