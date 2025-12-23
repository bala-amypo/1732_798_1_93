package com.example.demo.repository;

import com.example.demo.model.InteractionRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractionRuleRepository extends JpaRepository<InteractionRule, Long> {
    
    // Find rules where both medications are in the list
    @Query("SELECT r FROM InteractionRule r WHERE (r.medication1Id IN :ids AND r.medication2Id IN :ids)")
    List<InteractionRule> findByMedicationIds(@Param("ids") List<Long> medicationIds);
    
    // Find rules for specific medication pair
    List<InteractionRule> findByMedication1IdAndMedication2Id(Long med1Id, Long med2Id);
    
    // Find rules where medication is involved (either as medication1 or medication2)
    @Query("SELECT r FROM InteractionRule r WHERE r.medication1Id = :medId OR r.medication2Id = :medId")
    List<InteractionRule> findByMedicationId(@Param("medId") Long medicationId);
}