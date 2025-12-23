package com.example.demo.repository;

import com.example.demo.model.InteractionRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractionRuleRepository extends JpaRepository<InteractionRule, Long> {
    
    @Query("SELECT r FROM InteractionRule r WHERE (r.medication1Id IN :ids AND r.medication2Id IN :ids)")
    List<InteractionRule> findByMedicationIds(@Param("ids") List<Long> medicationIds);
    
    List<InteractionRule> findByMedication1IdAndMedication2Id(Long med1Id, Long med2Id);
    
    @Query("SELECT r FROM InteractionRule r WHERE r.medication1Id = :medId OR r.medication2Id = :medId")
    List<InteractionRule> findByMedicationId(@Param("medId") Long medicationId);
    
    // Add these methods for test compatibility
    @Query("SELECT r FROM InteractionRule r WHERE r.ingredientA.id = :ingredientId OR r.ingredientB.id = :ingredientId")
    List<InteractionRule> findByIngredientId(@Param("ingredientId") Long ingredientId);
    
    @Query("SELECT r FROM InteractionRule r WHERE (r.ingredientA.id = :ingredientId1 AND r.ingredientB.id = :ingredientId2) OR (r.ingredientA.id = :ingredientId2 AND r.ingredientB.id = :ingredientId1)")
    List<InteractionRule> findRuleBetweenIngredients(@Param("ingredientId1") Long ingredientId1, @Param("ingredientId2") Long ingredientId2);
}