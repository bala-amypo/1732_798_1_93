package com.example.demo.service;

import com.example.demo.dto.RuleRequest;
import com.example.demo.model.InteractionRule;
import java.util.List;
import java.util.Optional;

public interface RuleService {
    // Required by test
    InteractionRule addRule(RuleRequest ruleRequest);
    
    // Required by test
    Optional<InteractionRule> getRuleBetweenIngredients(Long ingredientId1, Long ingredientId2);
    
    // Your existing methods
    List<InteractionRule> getAllRules();
    Optional<InteractionRule> getRuleById(Long id);
    InteractionRule createRule(InteractionRule rule);
    InteractionRule addRule(InteractionRule rule);
    InteractionRule updateRule(Long id, InteractionRule ruleDetails);
    void deleteRule(Long id);
    List<InteractionRule> getRulesByMedicationId(Long medicationId);
}