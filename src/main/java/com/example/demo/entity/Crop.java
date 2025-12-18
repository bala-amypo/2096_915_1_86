package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "crops")
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double suitablePHMin;
    private double suitablePHMax;
    private double requiredWater;
    private String season;

    public Crop() {}

    public Crop(Long id, String name, double suitablePHMin, double suitablePHMax, double requiredWater, String season) {
        this.id = id;
        this.name = name;
        this.suitablePHMin = suitablePHMin;
        this.suitablePHMax = suitablePHMax;
        this.requiredWater = requiredWater;
        this.season = season;
    }

    // getters & setters …
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getSuitablePHMin() { return suitablePHMin; }
    public void setSuitablePHMin(double suitablePHMin) { this.suitablePHMin = suitablePHMin; }

    public double getSuitablePHMax() { return suitablePHMax; }
    public void setSuitablePHMax(double suitablePHMax) { this.suitablePHMax = suitablePHMax; }

    public double getRequiredWater() { return requiredWater; }
    public void setRequiredWater(double requiredWater) { this.requiredWater = requiredWater; }

    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }
}
