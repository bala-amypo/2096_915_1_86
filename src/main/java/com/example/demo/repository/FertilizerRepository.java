package com.example.demo.repository;

import com.example.demo.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
    // Query by fertilizer name
    List<Fertilizer> findByName(String name);

    // Query by recommended crops string
    List<Fertilizer> findByRecommendedForCrops(String crop);
}
