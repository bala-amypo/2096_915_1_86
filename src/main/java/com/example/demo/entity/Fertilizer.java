package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fertilizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fertilizer name
    private String name;

    // NPK ratio in format "N-P-K"
    private String npkRatio;

    // Recommended crops (simple string list, could be comma-separated)
    private String recommendedForCrops;
}
