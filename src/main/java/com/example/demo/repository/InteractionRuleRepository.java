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
    
    // Change from Optional<InteractionRule> to List<InteractionRule>
    @Query("SELECT r FROM InteractionRule r WHERE r.ingredientA.id = :ingredientId OR r.ingredientB.id = :ingredientId")
    List<InteractionRule> findByIngredientId(@Param("ingredientId") Long ingredientId);
    
    // Change from Optional<InteractionRule> to List<InteractionRule>
    @Query("SELECT r FROM InteractionRule r WHERE (r.ingredientA.id = :ingredientId1 AND r.ingredientB.id = :ingredientId2) OR (r.ingredientA.id = :ingredientId2 AND r.ingredientB.id = :ingredientId1)")
    List<InteractionRule> findRuleBetweenIngredients(@Param("ingredientId1") Long ingredientId1, @Param("ingredientId2") Long ingredientId2);
    
    // Keep your existing methods
    @Query("SELECT r FROM InteractionRule r WHERE (r.medication1Id IN :ids AND r.medication2Id IN :ids)")
    List<InteractionRule> findByMedicationIds(@Param("ids") List<Long> medicationIds);
    
    List<InteractionRule> findByMedication1IdAndMedication2Id(Long med1Id, Long med2Id);
    
    @Query("SELECT r FROM InteractionRule r WHERE r.medication1Id = :medId OR r.medication2Id = :medId")
    List<InteractionRule> findByMedicationId(@Param("medId") Long medicationId);
    
    // If you need an Optional version for single result, keep it with different name
    @Query("SELECT r FROM InteractionRule r WHERE r.id = :id")
    Optional<InteractionRule> findById(@Param("id") Long id);
}