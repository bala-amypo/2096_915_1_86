package com.example.demo.repository;

import com.example.demo.entity.Farm;
import java.util.List;

public interface FarmRepository {
    List<Farm> findByOwnerId(Long ownerId);
}
