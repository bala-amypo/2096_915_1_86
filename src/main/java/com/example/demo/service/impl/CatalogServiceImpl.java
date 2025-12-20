package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.CatalogService;
import com.example.demo.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class CatalogServiceImpl implements CatalogService {

    private final CropRepository cropRepo;
    private final FertilizerRepository fertRepo;

    public CatalogServiceImpl(CropRepository cropRepo,
                              FertilizerRepository fertRepo) {
        this.cropRepo = cropRepo;
        this.fertRepo = fertRepo;
    }

    @Override
    public Crop addCrop(Crop crop) {
        if (crop.getSuitablePHMin() > crop.getSuitablePHMax()) {
            throw new BadRequestException("PH min cannot be greater than max");
        }
        if (!ValidationUtil.validSeason(crop.getSeason())) {
            throw new BadRequestException("Invalid season");
        }
        return cropRepo.save(crop);
    }

    @Override
    public Fertilizer addFertilizer(Fertilizer f) {
        if (!f.getNpkRatio().matches("\\d+-\\d+-\\d+")) {
            throw new BadRequestException("NPK ratio invalid");
        }
        return fertRepo.save(f);
    }

  
    @Override
    public List<Crop> findSuitableCrops(
            Double ph, Double water, String season) {

        return cropRepo.findSuitableCrops(ph, season);
    }

    @Override
    public List<Fertilizer> findFertilizersForCrops(List<String> crops) {
        List<Fertilizer> list = new ArrayList<>();
        for (String c : crops) {
            list.addAll(fertRepo.findByCropName(c));
        }
        return list;
    }
}
