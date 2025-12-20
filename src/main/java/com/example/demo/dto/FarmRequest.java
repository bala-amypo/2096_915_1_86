package com.example.demo.dto;

public class FarmRequest {
    private String name;
    private Double soilPH;
    private Double waterLevel;
    private String season;

    public FarmRequest() {}

    public FarmRequest(String name, Double soilPH, Double waterLevel, String season) {
        this.name = name;
        this.soilPH = soilPH;
        this.waterLevel = waterLevel;
        this.season = season;
    }

    public String getName() {
        return name;
    }
    public Double getSoilPH() {
        return soilPH;
    }
    public Double getWaterLevel() {
        return waterLevel;
    }
    public String getSeason() {
        return season;
    }
}
