package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CatalogResponse;
import com.example.demo.entity.Fertilizer;
import com.example.demo.repository.FertilizerRepository;
import com.example.demo.service.CatalogService;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final FertilizerRepository fertRepo;

    public CatalogServiceImpl(FertilizerRepository fertRepo) {
        this.fertRepo = fertRepo;
    }

    @Override
    public CatalogResponse getCatalogByCropNames(List<String> cropNames) {

        List<Fertilizer> fertilizers =
                fertRepo.findByRecommendedForCropsIn(cropNames);

        CatalogResponse response = new CatalogResponse();
        response.setFertilizers(fertilizers);

        return response;
    }
}
