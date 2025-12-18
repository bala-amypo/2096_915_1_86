package com.example.demo.repository;

import com.example.demo.entity.Crop;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

    @Query("SELECT c FROM Crop c WHERE :ph BETWEEN c.suitablePHMin AND c.suitablePHMax AND c.season = :season")
    List<Crop> findSuitableCrops(double ph, String season);
}
