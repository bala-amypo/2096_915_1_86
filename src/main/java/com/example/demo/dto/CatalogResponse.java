package com.example.demo.dto;

import java.util.List;

public class CatalogResponse {

    private List<String> suitableCrops;
    private List<String> fertilizers;

    public CatalogResponse() {}

    public CatalogResponse(List<String> suitableCrops, List<String> fertilizers) {
        this.suitableCrops = suitableCrops;
        this.fertilizers = fertilizers;
    }

    public List<String> getSuitableCrops() {
        return suitableCrops;
    }

    public void setSuitableCrops(List<String> suitableCrops) {
        this.suitableCrops = suitableCrops;
    }

    public List<String> getFertilizers() {
        return fertilizers;
    }

    public void setFertilizers(List<String> fertilizers) {
        this.fertilizers = fertilizers;
    }
}
