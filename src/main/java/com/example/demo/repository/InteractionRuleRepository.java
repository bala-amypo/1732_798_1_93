package com.example.demo.repository;

import com.example.demo.model.InteractionRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InteractionRuleRepository extends JpaRepository<InteractionRule, Long> {
    
    // Example custom query - you may need to adjust based on your actual schema
    @Query("SELECT r FROM InteractionRule r WHERE r.medication1Id IN :ids AND r.medication2Id IN :ids")
    List<InteractionRule> findByMedicationIds(@Param("ids") List<Long> medicationIds);
    
    // Add this method to support the check
    default boolean existsByMedicationIds(List<Long> medicationIds) {
        return !findByMedicationIds(medicationIds).isEmpty();
    }
}