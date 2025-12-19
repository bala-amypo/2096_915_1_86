package com.example.demo.dto;

public class CropRequest {

    private String name;
    private double suitablePHMin;
    private double suitablePHMax;
    private double requiredWater;
    private String season;

    public CropRequest() {}

    public CropRequest(String name, double suitablePHMin, double suitablePHMax, double requiredWater, String season) {
        this.name = name;
        this.suitablePHMin = suitablePHMin;
        this.suitablePHMax = suitablePHMax;
        this.requiredWater = requiredWater;
        this.season = season;
    }

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
