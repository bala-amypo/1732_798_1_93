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
import java.util.Optional;

@Service
public class CatalogServiceImpl implements CatalogService {
    
    @Autowired
    private MedicationRepository medicationRepository;
    
    @Autowired
    private ActiveIngredientRepository ingredientRepository;
    
    @Override
    @Transactional
    public Medication addMedication(Medication medication) {
        return medicationRepository.save(medication);
    }
    
    @Override
    @Transactional
    public Medication updateMedication(Long id, Medication medicationDetails) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medication not found with id: " + id));
        
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
        
        return medicationRepository.save(medication);
    }
    
    @Override
    @Transactional
    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }
    
    @Override
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }
    
    @Override
    public Optional<Medication> getMedicationById(Long id) {
        return medicationRepository.findById(id);
    }
    
    @Override
    public List<Medication> getMedicationsByIngredientId(Long ingredientId) {
        return medicationRepository.findByIngredientId(ingredientId);
    }
    
    @Override
    @Transactional
    public ActiveIngredient addIngredient(ActiveIngredient ingredient) {
        return ingredientRepository.save(ingredient);
    }
    
    @Override
    @Transactional
    public ActiveIngredient updateIngredient(Long id, ActiveIngredient ingredientDetails) {
        ActiveIngredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found with id: " + id));
        
        if (ingredientDetails.getName() != null) {
            ingredient.setName(ingredientDetails.getName());
        }
        if (ingredientDetails.getChemicalName() != null) {
            ingredient.setChemicalName(ingredientDetails.getChemicalName());
        }
        if (ingredientDetails.getDescription() != null) {
            ingredient.setDescription(ingredientDetails.getDescription());
        }
        
        return ingredientRepository.save(ingredient);
    }
    
    @Override
    @Transactional
    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
    
    @Override
    public List<ActiveIngredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
    
    @Override
    public Optional<ActiveIngredient> getIngredientById(Long id) {
        return ingredientRepository.findById(id);
    }
    
    @Override
    public Optional<ActiveIngredient> getIngredientByName(String name) {
        return ingredientRepository.findByName(name);
    }
    
    @Override
    @Transactional
    public Medication addIngredientToMedication(Long medicationId, Long ingredientId) {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new RuntimeException("Medication not found"));
        
        ActiveIngredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        
        medication.addIngredient(ingredient);
        return medicationRepository.save(medication);
    }
    
    @Override
    @Transactional
    public Medication removeIngredientFromMedication(Long medicationId, Long ingredientId) {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new RuntimeException("Medication not found"));
        
        ActiveIngredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        
        medication.removeIngredient(ingredient);
        return medicationRepository.save(medication);
    }
}