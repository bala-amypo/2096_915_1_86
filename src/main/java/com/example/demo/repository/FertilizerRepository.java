package com.example.demo.repository;

import com.example.demo.entity.Fertilizer;
import java.util.List;

public interface FertilizerRepository {
    List<Fertilizer> findByCropName(String cropName);
}