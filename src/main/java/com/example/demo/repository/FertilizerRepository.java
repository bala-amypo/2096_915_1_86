package com.example.demo.repository;

import com.example.demo.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
    // Used in CatalogServiceImpl
    List<Fertilizer> findByRecommendedForCropsIn(List<String> cropNames);

    // Used in tests
    List<Fertilizer> findByCropName(String cropName);
}
