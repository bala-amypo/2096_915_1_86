
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.example.demo.entity.*;
public interface CropRepository extends JpaRepository<Crop, Long> {
    List<Crop> findSuitableCrops(Double ph, String season);
}