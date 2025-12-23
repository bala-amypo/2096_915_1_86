package com.example.demo.repository;

import com.example.demo.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

    Optional<Suggestion> findByFarmId(Long farmId);
}
