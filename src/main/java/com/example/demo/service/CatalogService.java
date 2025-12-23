package com.example.demo.service;

import com.example.demo.model.Medication;
import com.example.demo.model.ActiveIngredient;
import java.util.List;
import java.util.Optional;

public interface CatalogService {
    
    // Medication methods
    List<Medication> getAllMedications();
    Optional<Medication> getMedicationById(Long id);
    Medication createMedication(Medication medication);
    Medication updateMedication(Long id, Medication medicationDetails);
    void deleteMedication(Long id);
    List<Medication> searchMedications(String keyword);
    
    // ActiveIngredient methods (if you need them)
    List<ActiveIngredient> getAllIngredients();
    Optional<ActiveIngredient> getIngredientById(Long id);
    ActiveIngredient createIngredient(ActiveIngredient ingredient);
    ActiveIngredient updateIngredient(Long id, ActiveIngredient ingredientDetails);
    void deleteIngredient(Long id);
}