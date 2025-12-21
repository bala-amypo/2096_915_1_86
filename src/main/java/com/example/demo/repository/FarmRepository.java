package com.example.demo.repository;

import com.example.demo.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

    // Find all farms owned by a specific user
    List<Farm> findByOwnerId(Long ownerId);
}
