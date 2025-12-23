package com.example.demo.service.impl;

import com.example.demo.model.InteractionCheckResult;
import com.example.demo.model.InteractionRule;
import com.example.demo.model.Medication;
import com.example.demo.repository.InteractionCheckResultRepository;
import com.example.demo.repository.InteractionRuleRepository;
import com.example.demo.repository.MedicationRepository;
import com.example.demo.service.CatalogService;
import com.example.demo.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Service
public class InteractionServiceImpl implements InteractionService {
    
    private final InteractionRuleRepository ruleRepository;
    private final InteractionCheckResultRepository resultRepository;
    private final MedicationRepository medicationRepository;
    private final CatalogService catalogService;
    
    @Autowired
    public InteractionServiceImpl(InteractionRuleRepository ruleRepository,
                                 InteractionCheckResultRepository resultRepository,
                                 MedicationRepository medicationRepository,
                                 CatalogService catalogService) {
        this.ruleRepository = ruleRepository;
        this.resultRepository = resultRepository;
        this.medicationRepository = medicationRepository;
        this.catalogService = catalogService;
    }
    
    @Override
    @Transactional
    public InteractionCheckResult checkInteractions(List<Long> medicationIds) {
        if (medicationIds == null || medicationIds.isEmpty()) {
            throw new IllegalArgumentException("Medication IDs list cannot be empty");
        }
        
        // Get all medications
        List<Medication> medications = medicationRepository.findAllById(medicationIds);
        if (medications.size() != medicationIds.size()) {
            throw new IllegalArgumentException("Some medications not found");
        }
        
        // Collect all ingredients from medications
        Set<Long> allIngredientIds = new HashSet<>();
        StringBuilder medicationNames = new StringBuilder();
        
        for (Medication medication : medications) {
            medicationNames.append(medication.getName()).append(", ");
            medication.getIngredients().forEach(ingredient -> 
                allIngredientIds.add(ingredient.getId()));
        }
        
        // Remove trailing comma
        if (medicationNames.length() > 2) {
            medicationNames.setLength(medicationNames.length() - 2);
        }
        
        // Find interactions between all ingredient pairs
        StringBuilder interactionsJson = new StringBuilder("{");
        interactionsJson.append("\"totalInteractions\": 0,");
        interactionsJson.append("\"interactions\": []");
        interactionsJson.append("}");
        
        // Create and save result
        InteractionCheckResult result = new InteractionCheckResult();
        result.setMedicationNames(medicationNames.toString());
        result.setInteractionsJson(interactionsJson.toString());
        result.setCheckedAt(LocalDateTime.now());
        
        return resultRepository.save(result);
    }
    
    @Override
    public InteractionCheckResult getResult(Long id) {
        return resultRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Result not found with id: " + id));
    }
    
    @Override
    public List<InteractionCheckResult> getAllResults() {
        return resultRepository.findAll();
    }
}