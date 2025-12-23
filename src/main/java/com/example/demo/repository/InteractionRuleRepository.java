package com.example.demo.repository;

import com.example.demo.model.InteractionRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InteractionRuleRepository extends JpaRepository<InteractionRule, Long> {
    
    // Test expects this method to return Optional<InteractionRule>
    @Query("SELECT r FROM InteractionRule r WHERE " +
           "(r.ingredientA.id = :ingredientId1 AND r.ingredientB.id = :ingredientId2) OR " +
           "(r.ingredientA.id = :ingredientId2 AND r.ingredientB.id = :ingredientId1)")
    Optional<InteractionRule> findRuleBetweenIngredients(@Param("ingredientId1") Long ingredientId1, 
                                                        @Param("ingredientId2") Long ingredientId2);
    
    // Test also expects findByIngredients method (make it return Optional)
    @Query("SELECT r FROM InteractionRule r WHERE " +
           "(r.ingredientA.id = :ingredientId1 AND r.ingredientB.id = :ingredientId2) OR " +
           "(r.ingredientA.id = :ingredientId2 AND r.ingredientB.id = :ingredientId1)")
    Optional<InteractionRule> findByIngredients(@Param("ingredientId1") Long ingredientId1, 
                                               @Param("ingredientId2") Long ingredientId2);
    
    // Keep this method for your code - returns List
    @Query("SELECT r FROM InteractionRule r WHERE r.ingredientA.id = :ingredientId OR r.ingredientB.id = :ingredientId")
    java.util.List<InteractionRule> findByIngredientId(@Param("ingredientId") Long ingredientId);
    
    // Add this method for findByMedicationId (returns List)
    @Query("SELECT r FROM InteractionRule r WHERE r.medication1Id = :medicationId OR r.medication2Id = :medicationId")
    java.util.List<InteractionRule> findByMedicationId(@Param("medicationId") Long medicationId);
}