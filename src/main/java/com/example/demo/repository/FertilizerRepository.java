package com.example.demo.repository;

import com.example.demo.entity.Fertilizer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

    @Query("SELECT f FROM Fertilizer f WHERE lower(f.recommendedForCrops) LIKE %:crop%")
    List<Fertilizer> findByCropName(String crop);
}
