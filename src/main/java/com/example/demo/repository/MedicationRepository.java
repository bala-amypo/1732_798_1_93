package com.example.demo.repository;

import com.example.demo.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    
    @Query("SELECT m FROM Medication m JOIN m.ingredients i WHERE i.id = :ingredientId")
    List<Medication> findByIngredientId(@Param("ingredientId") Long ingredientId);
    
    // Add search methods if needed
    List<Medication> findByNameContainingIgnoreCase(String name);
    List<Medication> findByGenericNameContainingIgnoreCase(String genericName);
}