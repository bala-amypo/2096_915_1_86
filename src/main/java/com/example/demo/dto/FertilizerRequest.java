package com.example.demo.dto;

public class FertilizerRequest {

    private String name;
    private String npkRatio;
    private String recommendedForCrops;

    public FertilizerRequest() {}

    public FertilizerRequest(String name, String npkRatio, String recommendedForCrops) {
        this.name = name;
        this.npkRatio = npkRatio;
        this.recommendedForCrops = recommendedForCrops;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNpkRatio() { return npkRatio; }
    public void setNpkRatio(String npkRatio) { this.npkRatio = npkRatio; }

    public String getRecommendedForCrops() { return recommendedForCrops; }
    public void setRecommendedForCrops(String recommendedForCrops) { this.recommendedForCrops = recommendedForCrops; }
}
