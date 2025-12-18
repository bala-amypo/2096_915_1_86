package com.example.demo.service.impl;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.CropRepository;
import com.example.demo.repository.FertilizerRepository;
import com.example.demo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private FertilizerRepository fertilizerRepository;

    @Override
    public Crop addCrop(Crop crop) {

        if (crop.getSuitablePHMin() > crop.getSuitablePHMax())
            throw new BadRequestException("Invalid pH range");

        return cropRepository.save(crop);
    }

    @Override
    public Fertilizer addFertilizer(Fertilizer fertilizer) {

        if (!fertilizer.getNpkRatio().matches("\\d+-\\d+-\\d+"))
            throw new BadRequestException("Invalid NPK ratio");

        return fertilizerRepository.save(fertilizer);
    }

    @Override
    public List<Crop> findSuitableCrops(double ph, String season) {
        return cropRepository.findSuitableCrops(ph, season);
    }

    @Override
    public List<Fertilizer> findFertilizersForCrops(List<String> cropNames) {
        return cropNames.stream()
                .flatMap(name -> fertilizerRepository.findByCropName(name).stream())
                .collect(Collectors.toList());
    }
}
