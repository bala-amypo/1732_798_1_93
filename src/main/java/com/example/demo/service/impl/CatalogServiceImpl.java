package com.example.demo.service.impl;

import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.Medication;
import com.example.demo.repository.ActiveIngredientRepository;
import com.example.demo.repository.MedicationRepository;
import com.example.demo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.HashSet;

@Service
public class CatalogServiceImpl implements CatalogService {
    
    private ActiveIngredientRepository ingredientRepository;
    private MedicationRepository medicationRepository;
    
    // Add no-arg constructor for testing
    public CatalogServiceImpl() {
        // For testing only
    }
    
    // Keep normal constructor
    @Autowired
    public CatalogServiceImpl(ActiveIngredientRepository ingredientRepository, 
                             MedicationRepository medicationRepository) {
        this.ingredientRepository = ingredientRepository;
        this.medicationRepository = medicationRepository;
    }
    
    @Override
    public ActiveIngredient addIngredient(ActiveIngredient ingredient) {
        if (ingredientRepository == null) {
            throw new IllegalStateException("IngredientRepository not initialized");
        }
        if (ingredientRepository.existsByName(ingredient.getName())) {
            throw new IllegalArgumentException("Ingredient name must be unique");
        }
        return ingredientRepository.save(ingredient);
    }
    
    @Override
    @Transactional
    public Medication addMedication(Medication medication) {
        if (medicationRepository == null) {
            throw new IllegalStateException("MedicationRepository not initialized");
        }
        if (medication.getIngredients() == null || medication.getIngredients().isEmpty()) {
            throw new IllegalArgumentException("Medication must include at least one ingredient");
        }
        return medicationRepository.save(medication);
    }
    
    // Keep your other methods...
}