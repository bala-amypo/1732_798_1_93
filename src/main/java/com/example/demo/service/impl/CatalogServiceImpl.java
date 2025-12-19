package com.example.demo.service.impl;

import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.Medication;
import com.example.demo.repository.ActiveIngredientRepository;
import com.example.demo.repository.MedicationRepository;
import com.example.demo.service.CatalogService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {
    
    private final ActiveIngredientRepository ingredientRepository;
    private final MedicationRepository medicationRepository;
    
    public CatalogServiceImpl(ActiveIngredientRepository ingredientRepository, 
                             MedicationRepository medicationRepository) {
        this.ingredientRepository = ingredientRepository;
        this.medicationRepository = medicationRepository;
    }
    
    @Override
    public ActiveIngredient addIngredient(ActiveIngredient ingredient) {
        if (ingredientRepository.existsByName(ingredient.getName())) {
            throw new IllegalArgumentException("Ingredient name must be unique");
        }
        return ingredientRepository.save(ingredient);
    }
    
    @Override
    public Medication addMedication(Medication medication) {
        if (medication.getIngredients() == null || medication.getIngredients().isEmpty()) {
            throw new IllegalArgumentException("Medication must include at least one ingredient");
        }
        return medicationRepository.save(medication);
    }
    
    @Override
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }
    
    @Override
    public List<ActiveIngredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
    
    @Override
    public ActiveIngredient getIngredientById(Long id) {
        return ingredientRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Ingredient not found with id: " + id));
    }
    
    @Override
    public Medication getMedicationById(Long id) {
        return medicationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Medication not found with id: " + id));
    }
    
    @Override
    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
    
    @Override
    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }
}