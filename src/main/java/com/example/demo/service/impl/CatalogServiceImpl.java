package com.example.demo.service.impl;

import com.example.demo.model.Medication;
import com.example.demo.repository.MedicationRepository;
import com.example.demo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogServiceImpl implements CatalogService {
    
    @Autowired
    private MedicationRepository medicationRepository;
    
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
}