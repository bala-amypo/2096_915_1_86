

package com.example.demo.repository;

import com.example.demo.entity.Crop;
import java.util.List;

public interface CropRepository {
    List<Crop> findSuitableCrops(Double ph, String season);
}