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
    
    // The test expects this exact method name
    @Query("SELECT r FROM InteractionRule r WHERE " +
           "(r.ingredientA.id = :ingredientId1 AND r.ingredientB.id = :ingredientId2) OR " +
           "(r.ingredientA.id = :ingredientId2 AND r.ingredientB.id = :ingredientId1)")
    Optional<InteractionRule> findRuleBetweenIngredients(@Param("ingredientId1") Long ingredientId1, 
                                                        @Param("ingredientId2") Long ingredientId2);
    
    // Keep your existing methods too
    @Query("SELECT r FROM InteractionRule r WHERE " +
           "(r.ingredientA.id = :ingredientId1 AND r.ingredientB.id = :ingredientId2) OR " +
           "(r.ingredientA.id = :ingredientId2 AND r.ingredientB.id = :ingredientId1)")
    Optional<InteractionRule> findByIngredients(@Param("ingredientId1") Long ingredientId1, 
                                               @Param("ingredientId2") Long ingredientId2);
    
    // Add other methods as needed
    @Query("SELECT r FROM InteractionRule r WHERE " +
           "r.ingredientA.id = :ingredientId OR r.ingredientB.id = :ingredientId")
    List<InteractionRule> findByIngredientId(@Param("ingredientId") Long ingredientId);
}