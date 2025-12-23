package com.example.demo.service;

import com.example.demo.model.InteractionRule;
import java.util.List;
import java.util.Optional;

public interface RuleService {
    List<InteractionRule> getAllRules();
    Optional<InteractionRule> getRuleById(Long id);
    InteractionRule createRule(InteractionRule rule);
    
    // Add alias method for test compatibility
    InteractionRule addRule(InteractionRule rule);
    
    InteractionRule updateRule(Long id, InteractionRule ruleDetails);
    void deleteRule(Long id);
    List<InteractionRule> getRulesByMedicationId(Long medicationId);
    
    // CHANGE THIS: Return List<InteractionRule> not Optional<InteractionRule>
    List<InteractionRule> getRuleBetweenIngredients(Long ingredientId1, Long ingredientId2);
}