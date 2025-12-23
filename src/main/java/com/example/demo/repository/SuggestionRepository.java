
package com.example.demo.repository;

import com.example.demo.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    // ✅ Custom method to avoid clashing with Spring Data's findById
    List<Suggestion> findByFarmId(long farmId);
}
