package com.example.demo.service;

import com.example.demo.dto.RuleRequest;
import com.example.demo.model.InteractionRule;
import java.util.List;
import java.util.Optional;

public interface RuleService {
    
    // Method with RuleRequest
    InteractionRule addRule(RuleRequest ruleRequest);
    
    // Method with InteractionRule (alias)
    InteractionRule addRule(InteractionRule rule);
    InteractionRule createRule(InteractionRule rule);
    
    // Get rule between two ingredients
    Optional<InteractionRule> getRuleBetweenIngredients(Long ingredientId1, Long ingredientId2);
    
    // Get all rules
    List<InteractionRule> getAllRules();
    
    // Get rule by ID
    Optional<InteractionRule> getRuleById(Long id);
    
    // Update rule
    InteractionRule updateRule(Long id, InteractionRule ruleDetails);
    
    // Delete rule
    void deleteRule(Long id);
    
    // Get rules by medication ID
    List<InteractionRule> getRulesByMedicationId(Long medicationId);
    
    // Optional: Get rules by ingredient ID
    List<InteractionRule> getRulesByIngredientId(Long ingredientId);
    
    // Optional: Get rules by severity
    List<InteractionRule> getRulesBySeverity(String severity);
}