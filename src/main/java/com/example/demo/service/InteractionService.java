package com.example.demo.service;

import com.example.demo.model.InteractionCheckResult;
import com.example.demo.model.InteractionRule;
import com.example.demo.repository.InteractionCheckResultRepository;
import com.example.demo.repository.InteractionRuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class InteractionService {
    
    private final InteractionRuleRepository ruleRepository;
    private final InteractionCheckResultRepository resultRepository;
    
    public InteractionService(InteractionRuleRepository ruleRepository,
                             InteractionCheckResultRepository resultRepository) {
        this.ruleRepository = ruleRepository;
        this.resultRepository = resultRepository;
    }
    
    public InteractionCheckResult checkInteractions(List<Long> medicationIds) {
        // For now, return a simple result
        // In a real implementation, you would check interactions between medications
        
        InteractionCheckResult result = new InteractionCheckResult();
        result.setMedicationNames("Test Medications");
        result.setInteractions("No interactions found");
        result.setTotalInteractions(0);
        result.setCriticalInteractions(0);
        result.setHasInteractions(false);
        
        return resultRepository.save(result);
    }
    
    public InteractionCheckResult getResult(Long id) {
        return resultRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Result not found with id: " + id));
    }
}