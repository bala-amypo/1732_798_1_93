package com.example.demo.repository;

import com.example.demo.model.ActiveIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ActiveIngredientRepository extends JpaRepository<ActiveIngredient, Long> {
    Optional<ActiveIngredient> findByName(String name);
    
    // Add this method for test compatibility
    boolean existsByName(String name);
}