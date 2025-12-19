package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "suggestions")
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Farm farm;

    private String suggestedCrops;

    private String suggestedFertilizers;

    private LocalDateTime createdAt;

    public Suggestion() {}

    public Suggestion(Long id, Farm farm, String suggestedCrops, String suggestedFertilizers, LocalDateTime createdAt) {
        this.id = id;
        this.farm = farm;
        this.suggestedCrops = suggestedCrops;
        this.suggestedFertilizers = suggestedFertilizers;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

   
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Farm getFarm() { return farm; }
    public void setFarm(Farm farm) { this.farm = farm; }

    public String getSuggestedCrops() { return suggestedCrops; }
    public void setSuggestedCrops(String suggestedCrops) { this.suggestedCrops = suggestedCrops; }

    public String getSuggestedFertilizers() { return suggestedFertilizers; }
    public void setSuggestedFertilizers(String suggestedFertilizers) { this.suggestedFertilizers = suggestedFertilizers; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
