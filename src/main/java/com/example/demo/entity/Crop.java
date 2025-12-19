// src/main/java/com/example/demo/entity/Crop.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Crop {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double suitablePHMin;
    private Double suitablePHMax;
    private String season;
    private Double requiredWater;
}
