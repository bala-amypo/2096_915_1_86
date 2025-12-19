package com.example.demo.dto;

public class FarmRequest {

    private String farmName;
    private double soilPH;
    private double waterLevel;
    private String season;

    public FarmRequest() {}

    public FarmRequest(String farmName, double soilPH, double waterLevel, String season) {
        this.farmName = farmName;
        this.soilPH = soilPH;
        this.waterLevel = waterLevel;
        this.season = season;
    }

    public String getFarmName() { return farmName; }
    public void setFarmName(String farmName) { this.farmName = farmName; }

    public double getSoilPH() { return soilPH; }
    public void setSoilPH(double soilPH) { this.soilPH = soilPH; }

    public double getWaterLevel() { return waterLevel; }
    public void setWaterLevel(double waterLevel) { this.waterLevel = waterLevel; }

    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }
}
