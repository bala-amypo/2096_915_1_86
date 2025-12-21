package com.example.demo.service.impl;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.CropRepository;
import com.example.demo.repository.FertilizerRepository;
import com.example.demo.service.CatalogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {

    private final CropRepository cropRepo;
    private final FertilizerRepository fertRepo;

    private static final Set<String> VALID_SEASONS = Set.of("Kharif", "Rabi", "Summer");
    private static final Pattern NPK_PATTERN = Pattern.compile("^\\d+-\\d+-\\d+$");

    public CatalogServiceImpl(CropRepository cropRepo,
                              FertilizerRepository fertRepo) {
        this.cropRepo = cropRepo;
        this.fertRepo = fertRepo;
    }

    @Override
    public Crop addCrop(Crop crop) {
        if (crop.getSuitablePHMin() > crop.getSuitablePHMax()) {
            throw new BadRequestException("PH min invalid");
        }
        if (!VALID_SEASONS.contains(crop.getSeason())) {
            throw new BadRequestException("Invalid season");
        }
        return cropRepo.save(crop);
    }

    @Override
    public Fertilizer addFertilizer(Fertilizer fertilizer) {
        if (!NPK_PATTERN.matcher(fertilizer.getNpkRatio()).matches()) {
            throw new BadRequestException("NPK invalid");
        }
        return fertRepo.save(fertilizer);
    }

    @Override
    public List<Crop> findSuitableCrops(Double ph, Double waterLevel, String season) {
        return cropRepo.findSuitableCrops(ph, waterLevel, season);
    }

    @Override
    public List<Fertilizer> findFertilizersForCrops(List<String> cropNames) {
        return fertRepo.findByRecommendedForCropsIn(cropNames);
    }
}
