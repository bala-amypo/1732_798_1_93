package com.example.demo.service;

import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.Medication;
import com.example.demo.repository.ActiveIngredientRepository;
import com.example.demo.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
    
    private final ActiveIngredientRepository ingredientRepository;
    private final MedicationRepository medicationRepository;
    
    public CatalogService(ActiveIngredientRepository ingredientRepository, 
                         MedicationRepository medicationRepository) {
        this.ingredientRepository = ingredientRepository;
        this.medicationRepository = medicationRepository;
    }
    
    public ActiveIngredient addIngredient(ActiveIngredient ingredient) {
        if (ingredientRepository.existsByName(ingredient.getName())) {
            throw new IllegalArgumentException("Ingredient name must be unique");
        }
        return ingredientRepository.save(ingredient);
    }
    
    public Medication addMedication(Medication medication) {
        if (medication.getIngredients() == null || medication.getIngredients().isEmpty()) {
            throw new IllegalArgumentException("Medication must include at least one ingredient");
        }
        return medicationRepository.save(medication);
    }
    
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }
}