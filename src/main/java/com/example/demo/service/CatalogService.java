package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.CatalogResponse;

public interface CatalogService {

    CatalogResponse getCatalogByCropNames(List<String> cropNames);
}
