// src/main/java/com/example/demo/entity/Suggestion.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Suggestion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String suggestedCrops;        // Comma-separated names
    private String suggestedFertilizers;  // Comma-separated names

    @ManyToOne
    private Farm farm;

    private LocalDateTime createdAt;

    // Explicitly callable and also usable as JPA lifecycle callback
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
