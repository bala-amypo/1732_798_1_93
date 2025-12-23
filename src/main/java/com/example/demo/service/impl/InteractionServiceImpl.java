package com.example.demo.service.impl;

import com.example.demo.model.InteractionCheckResult;
import com.example.demo.repository.InteractionCheckResultRepository;
import com.example.demo.repository.InteractionRuleRepository;
import com.example.demo.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractionServiceImpl implements InteractionService {
    
    @Autowired
    private InteractionRuleRepository ruleRepository;
    
    @Autowired
    private InteractionCheckResultRepository resultRepository;
    
    @Override
    public InteractionCheckResult checkInteractions(List<String> medicationNames) {
        // Simplified logic for compilation
        String medications = String.join(", ", medicationNames);
        
        // Check for interactions (simplified logic)
        boolean hasInteraction = checkForInteractions(medicationNames);
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
    
    private boolean checkForInteractions(List<String> medicationNames) {
        // Simplified interaction check logic
        // You can implement more complex logic here
        return medicationNames.size() > 1;
    }
}