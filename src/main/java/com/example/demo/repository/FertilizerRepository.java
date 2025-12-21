package com.example.demo.repository;

import com.example.demo.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

    List<Fertilizer> findByRecommendedForCropsIn(List<String> cropNames);

    List<Fertilizer> findByCropName(String cropName);
}
