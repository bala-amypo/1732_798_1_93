package com.example.demo.service.impl;

import com.example.demo.model.InteractionCheckResult;
import com.example.demo.model.Medication;
import com.example.demo.repository.InteractionCheckResultRepository;
import com.example.demo.repository.InteractionRuleRepository;
import com.example.demo.repository.MedicationRepository;
import com.example.demo.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InteractionServiceImpl implements InteractionService {
    
    @Autowired
    private InteractionRuleRepository ruleRepository;
    
    @Autowired
    private InteractionCheckResultRepository resultRepository;
    
    @Autowired
    private MedicationRepository medicationRepository;
    
    @Override
    public InteractionCheckResult checkInteractions(List<Long> medicationIds) {
        // Get medication names from IDs
        List<String> medicationNames = medicationIds.stream()
                .map(id -> medicationRepository.findById(id)
                        .map(Medication::getName)
                        .orElse("Unknown Medication ID: " + id))
                .collect(Collectors.toList());
        
        String medications = String.join(", ", medicationNames);
        
        // Check for interactions
        boolean hasInteraction = checkForInteractions(medicationIds);
        String interactionDetails = hasInteraction ? 
            "Potential interaction found between medications" : 
            "No interactions found";
        
        // Create and save result
        InteractionCheckResult result = new InteractionCheckResult(
            medications, 
            interactionDetails, 
            hasInteraction
        );
        
        return resultRepository.save(result);
    }
    
    @Override
    public List<InteractionCheckResult> getAllResults() {
        return resultRepository.findAll();
    }
    
    @Override
    public InteractionCheckResult getResult(Long id) {
        return resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found with id: " + id));
    }
    
    private boolean checkForInteractions(List<Long> medicationIds) {
        // Simplified interaction check logic
        // In real implementation, check rules in InteractionRuleRepository
        return medicationIds.size() > 1 && ruleRepository.existsByMedicationIds(medicationIds);
    }
}