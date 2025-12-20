package com.example.demo.repository;

import com.example.demo.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropRepository extends JpaRepository<Crop, Long> {

    // Updated to accept 3 parameters: ph, waterLevel, season
    List<Crop> findSuitableCrops(Double ph, Double waterLevel, String season);
}
