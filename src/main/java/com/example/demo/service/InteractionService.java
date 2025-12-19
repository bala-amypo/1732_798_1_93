package com.example.demo.service;

import com.example.demo.model.InteractionRule;
import com.example.demo.repository.InteractionRuleRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InteractionService {
    
    private final InteractionRuleRepository ruleRepository;
    
    public InteractionService(InteractionRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }
    
    public Map<String, List<InteractionRule>> checkInteractions(List<Long> ingredientIds) {
        Map<String, List<InteractionRule>> result = new HashMap<>();
        
        for (int i = 0; i < ingredientIds.size(); i++) {
            for (int j = i + 1; j < ingredientIds.size(); j++) {
                // FIXED: Changed method name from findRuleBetweenIngredients to findByIngredients
                Optional<InteractionRule> rule = ruleRepository.findByIngredients(
                    ingredientIds.get(i), ingredientIds.get(j));
                
                if (rule.isPresent() && rule.get().getActive()) {
                    result.computeIfAbsent(rule.get().getSeverity(), k -> new ArrayList<>())
                          .add(rule.get());
                }
            }
        }
        
        return result;
    }
}