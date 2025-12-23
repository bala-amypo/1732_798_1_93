package com.example.demo.service;

import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.Medication;
import java.util.List;

public interface CatalogService {
    ActiveIngredient addIngredient(ActiveIngredient ingredient);
    Medication addMedication(Medication medication);
    List<Medication> getAllMedications();
    List<ActiveIngredient> getAllIngredients();
    ActiveIngredient getIngredientById(Long id);
    Medication getMedicationById(Long id);
    void deleteIngredient(Long id);
    void deleteMedication(Long id);  // Make sure this method exists
}