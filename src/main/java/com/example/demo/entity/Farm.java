package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "farms")
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String farmName;
    private double soilPH;
    private double waterLevel;
    private String season;

    @ManyToOne
    private User owner;

   
    public Farm() {
    }

    
    public Farm(Long id, String farmName, double soilPH, double waterLevel, String season, User owner) {
        this.id = id;
        this.farmName = farmName;
        this.soilPH = soilPH;
        this.waterLevel = waterLevel;
        this.season = season;
        this.owner = owner;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public double getSoilPH() {
        return soilPH;
    }

    public void setSoilPH(double soilPH) {
        this.soilPH = soilPH;
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
