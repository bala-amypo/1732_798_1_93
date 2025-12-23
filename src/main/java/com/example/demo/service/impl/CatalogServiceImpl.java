package com.example.demo.service.impl;

import com.example.demo.model.Medication;
import com.example.demo.model.ActiveIngredient;
import com.example.demo.repository.MedicationRepository;
import com.example.demo.repository.ActiveIngredientRepository;
import com.example.demo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogServiceImpl implements CatalogService {
    
    @Autowired
    private MedicationRepository medicationRepository;
    
    @Autowired
    private ActiveIngredientRepository activeIngredientRepository;
    
    // Medication methods
    @Override
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }
    
    @Override
    public Optional<Medication> getMedicationById(Long id) {
        return medicationRepository.findById(id);
    }
    
    @Override
    public Medication createMedication(Medication medication) {
        return medicationRepository.save(medication);
    }
    
    @Override
    public Medication updateMedication(Long id, Medication medicationDetails) {
        return medicationRepository.findById(id).map(medication -> {
            if (medicationDetails.getName() != null) {
                medication.setName(medicationDetails.getName());
            }
            if (medicationDetails.getGenericName() != null) {
                medication.setGenericName(medicationDetails.getGenericName());
            }
            if (medicationDetails.getDescription() != null) {
                medication.setDescription(medicationDetails.getDescription());
            }
            if (medicationDetails.getDosageForm() != null) {
                medication.setDosageForm(medicationDetails.getDosageForm());
            }
            if (medicationDetails.getStrength() != null) {
                medication.setStrength(medicationDetails.getStrength());
            }
            if (medicationDetails.getIngredients() != null) {
                medication.setIngredients(medicationDetails.getIngredients());
            }
            return medicationRepository.save(medication);
        }).orElseThrow(() -> new RuntimeException("Medication not found with id: " + id));
    }
    
    @Override
    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }
    
    @Override
    public List<Medication> searchMedications(String keyword) {
        return medicationRepository.findByNameContainingIgnoreCase(keyword);
    }
    
    // ActiveIngredient methods
    @Override
    public List<ActiveIngredient> getAllIngredients() {
        return activeIngredientRepository.findAll();
    }
    
    @Override
    public Optional<ActiveIngredient> getIngredientById(Long id) {
        return activeIngredientRepository.findById(id);
    }
    
    @Override
    public ActiveIngredient createIngredient(ActiveIngredient ingredient) {
        return activeIngredientRepository.save(ingredient);
    }
    
    @Override
    public ActiveIngredient updateIngredient(Long id, ActiveIngredient ingredientDetails) {
        return activeIngredientRepository.findById(id).map(ingredient -> {
            if (ingredientDetails.getName() != null) {
                ingredient.setName(ingredientDetails.getName());
            }
            if (ingredientDetails.getChemicalName() != null) {
                ingredient.setChemicalName(ingredientDetails.getChemicalName());
            }
            if (ingredientDetails.getDescription() != null) {
                ingredient.setDescription(ingredientDetails.getDescription());
            }
            return activeIngredientRepository.save(ingredient);
        }).orElseThrow(() -> new RuntimeException("ActiveIngredient not found with id: " + id));
    }
    
    @Override
    public void deleteIngredient(Long id) {
        activeIngredientRepository.deleteById(id);
    }
}