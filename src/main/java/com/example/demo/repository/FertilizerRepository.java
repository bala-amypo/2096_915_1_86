package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Fertilizer;

public interface FertilizerRepository extends JpaRepository<Fertilizer, Integer> {

    List<Fertilizer> findByRecommendedForCropsIn(List<String> cropNames);

}

