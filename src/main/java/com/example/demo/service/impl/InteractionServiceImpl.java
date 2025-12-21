package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.CatalogService;
import com.example.demo.service.InteractionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class InteractionServiceImpl implements InteractionService {
    
    private final InteractionRuleRepository ruleRepository;
    private final InteractionCheckResultRepository resultRepository;
    private final MedicationRepository medicationRepository;
    private final CatalogService catalogService;
    
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
    public InteractionCheckResult checkInteractions(List<Long> medicationIds) {
        return checkInteractions(medicationIds, null, null, null);
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
    
    // Helper method with additional parameters
    public InteractionCheckResult checkInteractions(List<Long> medicationIds, Long userId, 
                                                   String userEmail, String sessionId) {
        if (medicationIds == null || medicationIds.size() < 2) {
            throw new IllegalArgumentException("Need at least 2 medications to check interactions");
        }
        
        // Get medications
        List<Medication> medications = new ArrayList<>();
        for (Long id : medicationIds) {
            medications.add(catalogService.getMedicationById(id));
        }
        
        // Extract all unique ingredients from all medications
        Set<ActiveIngredient> allIngredients = new HashSet<>();
        for (Medication medication : medications) {
            allIngredients.addAll(medication.getIngredients());
        }
        
        // Check for interactions between all ingredient pairs
        List<String> interactionList = new ArrayList<>();
        int totalInteractions = 0;
        int criticalInteractions = 0;
        
        List<ActiveIngredient> ingredientList = new ArrayList<>(allIngredients);
        for (int i = 0; i < ingredientList.size(); i++) {
            for (int j = i + 1; j < ingredientList.size(); j++) {
                ActiveIngredient ingA = ingredientList.get(i);
                ActiveIngredient ingB = ingredientList.get(j);
                
                Optional<InteractionRule> rule = ruleRepository.findByIngredients(
                    ingA.getId(), ingB.getId());
                
                if (rule.isPresent()) {
                    totalInteractions++;
                    if ("CRITICAL".equalsIgnoreCase(rule.get().getSeverity())) {
                        criticalInteractions++;
                    }
                    interactionList.add(String.format("%s & %s: %s (Severity: %s)", 
                        ingA.getName(), ingB.getName(), 
                        rule.get().getDescription(), 
                        rule.get().getSeverity()));
                }
            }
        }
        
        // Build medication IDs string
        String medicationIdsStr = medicationIds.stream()
            .map(String::valueOf)
            .reduce((a, b) -> a + "," + b)
            .orElse("");
        
        // Create result object
        InteractionCheckResult result = new InteractionCheckResult();
        result.setMedicationNames(buildMedicationNames(medications));
        result.setMedicationIds(medicationIdsStr);
        result.setInteractions(String.join("\n", interactionList));
        result.setTotalInteractions(totalInteractions);
        result.setCriticalInteractions(criticalInteractions);
        result.setHasInteractions(totalInteractions > 0);
        
        // Set user info if provided
        if (userId != null) {
            result.setUserId(userId);
        }
        if (userEmail != null) {
            result.setUserEmail(userEmail);
        }
        if (sessionId != null) {
            result.setSessionId(sessionId);
        }
        
        // Set checkedAt
        result.setCheckedAt(LocalDateTime.now());
        
        return resultRepository.save(result);
    }
    
    private String buildMedicationNames(List<Medication> medications) {
        List<String> names = new ArrayList<>();
        for (Medication med : medications) {
            names.add(med.getName());
        }
        return String.join(", ", names);
    }
}