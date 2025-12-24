package com.example.demo.service;

import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.Medication;
import java.util.List;
import java.util.Optional;

public interface CatalogService {
    
    // Medication operations
    Medication addMedication(Medication medication);
    Medication createMedication(Medication medication);  // Add this
    Medication updateMedication(Long id, Medication medicationDetails);
    void deleteMedication(Long id);
    List<Medication> getAllMedications();
    Optional<Medication> getMedicationById(Long id);
    List<Medication> getMedicationsByIngredientId(Long ingredientId);
    List<Medication> searchMedications(String query);  // Add this
    
    // Ingredient operations
    ActiveIngredient addIngredient(ActiveIngredient ingredient);
    ActiveIngredient createIngredient(ActiveIngredient ingredient);  // Add this
    ActiveIngredient updateIngredient(Long id, ActiveIngredient ingredientDetails);
    void deleteIngredient(Long id);
    List<ActiveIngredient> getAllIngredients();
    Optional<ActiveIngredient> getIngredientById(Long id);
    Optional<ActiveIngredient> getIngredientByName(String name);
    
    // Relationship operations
    Medication addIngredientToMedication(Long medicationId, Long ingredientId);
    Medication removeIngredientFromMedication(Long medicationId, Long ingredientId);
}