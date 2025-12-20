package com.example.demo.dto;

public class CropRequest {
    private String name;
    private Double suitablePHMin;
    private Double suitablePHMax;
    private Double requiredWater;
    private String season;

    public CropRequest() {}

    public CropRequest(String name, Double min, Double max, Double water, String season) {
        this.name = name;
        this.suitablePHMin = min;
        this.suitablePHMax = max;
        this.requiredWater = water;
        this.season = season;
    }

    public String getName() {
        return name;
    }
    public Double getSuitablePHMin() {
        return suitablePHMin;
    }
    public Double getSuitablePHMax() {
        return suitablePHMax;
    }
    public Double getRequiredWater() {
        return requiredWater;
    }
    public String getSeason() {
        return season;
    }
}
