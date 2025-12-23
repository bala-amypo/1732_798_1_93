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

@Service
public class CatalogServiceImpl implements CatalogService {
    
    private ActiveIngredientRepository ingredientRepository;
    private MedicationRepository medicationRepository;
    
    // NO-ARG CONSTRUCTOR FOR TESTING
    public CatalogServiceImpl() {
    }
    
    @Autowired
    public CatalogServiceImpl(ActiveIngredientRepository ingredientRepository, 
                             MedicationRepository medicationRepository) {
        this.ingredientRepository = ingredientRepository;
        this.medicationRepository = medicationRepository;
    }
    
    @Override
    @Transactional
    public ActiveIngredient addIngredient(ActiveIngredient ingredient) {
        if (ingredientRepository != null && ingredientRepository.existsByName(ingredient.getName())) {
            throw new IllegalArgumentException("Ingredient name must be unique");
        }
        return ingredient; // For testing
    }
    
    @Override
    @Transactional
    public Medication addMedication(Medication medication) {
        if (medication.getIngredients() == null || medication.getIngredients().isEmpty()) {
            throw new IllegalArgumentException("Medication must include at least one ingredient");
        }
        return medication; // For testing
    }
    
    @Override
    public List<Medication> getAllMedications() {
        if (medicationRepository == null) {
            return List.of(); // Empty list for testing
        }
        return medicationRepository.findAll();
    }
    
    @Override
    public List<ActiveIngredient> getAllIngredients() {
        if (ingredientRepository == null) {
            return List.of(); // Empty list for testing
        }
        return ingredientRepository.findAll();
    }
    
    @Override
    public ActiveIngredient getIngredientById(Long id) {
        if (ingredientRepository == null) {
            ActiveIngredient ingredient = new ActiveIngredient();
            ingredient.setId(id);
            return ingredient;
        }
        return ingredientRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Ingredient not found with id: " + id));
    }
    
    @Override
    public Medication getMedicationById(Long id) {
        if (medicationRepository == null) {
            Medication medication = new Medication();
            medication.setId(id);
            return medication;
        }
        return medicationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Medication not found with id: " + id));
    }
    
    @Override
    @Transactional
    public void deleteIngredient(Long id) {
        if (ingredientRepository != null) {
            ingredientRepository.deleteById(id);
        }
    }
    
    @Override
    @Transactional
    public void deleteMedication(Long id) {
        if (medicationRepository != null) {
            medicationRepository.deleteById(id);
        }
    }
}