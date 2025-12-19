package com.example.demo.repository;

import com.example.demo.model.InteractionRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InteractionRuleRepository extends JpaRepository<InteractionRule, Long> {
    
    // Find interaction between two specific ingredients
    @Query("SELECT r FROM InteractionRule r WHERE " +
           "(r.ingredientA.id = :ingredientId1 AND r.ingredientB.id = :ingredientId2) OR " +
           "(r.ingredientA.id = :ingredientId2 AND r.ingredientB.id = :ingredientId1)")
    Optional<InteractionRule> findByIngredients(@Param("ingredientId1") Long ingredientId1, 
                                               @Param("ingredientId2") Long ingredientId2);
    
    // Find all interactions for a specific ingredient - FIX THIS METHOD
    @Query("SELECT r FROM InteractionRule r WHERE " +
           "r.ingredientA.id = :ingredientId OR r.ingredientB.id = :ingredientId")
    List<InteractionRule> findByIngredientId(@Param("ingredientId") Long ingredientId);
    
    // Find all active interactions
    List<InteractionRule> findByActiveTrue();
    
    // Find interactions by severity
    List<InteractionRule> findBySeverity(String severity);
    
    // Find interactions by severity and active status
    List<InteractionRule> findBySeverityAndActiveTrue(String severity);
    
    // Check if interaction exists between two ingredients
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END FROM InteractionRule r WHERE " +
           "(r.ingredientA.id = :ingredientId1 AND r.ingredientB.id = :ingredientId2) OR " +
           "(r.ingredientA.id = :ingredientId2 AND r.ingredientB.id = :ingredientId1)")
    boolean existsByIngredients(@Param("ingredientId1") Long ingredientId1, 
                               @Param("ingredientId2") Long ingredientId2);
}