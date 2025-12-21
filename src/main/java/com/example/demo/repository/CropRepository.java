
package com.example.demo.repository;

import com.example.demo.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CropRepository extends JpaRepository<Crop, Long> {

    @Query("""
        select c from Crop c
        where :ph between c.suitablePHMin and c.suitablePHMax
        and c.requiredWater <= :water
        and c.season = :season
    """)
    List<Crop> findSuitableCrops(
            @Param("ph") Double ph,
            @Param("water") Double water,
            @Param("season") String season
    );

    @Query("""
        select c from Crop c
        where :ph between c.suitablePHMin and c.suitablePHMax
        and c.season = :season
    """)
    List<Crop> findSuitableCrops(
            @Param("ph") Double ph,
            @Param("season") String season
    );
}
