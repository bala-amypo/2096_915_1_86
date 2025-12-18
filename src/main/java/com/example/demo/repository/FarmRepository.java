package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Farm;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

    List<Farm> findByOwnerId(Long ownerId);
}
