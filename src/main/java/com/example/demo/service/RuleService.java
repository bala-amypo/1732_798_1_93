package com.example.demo.service;

import com.example.demo.dto.RuleRequest;
import com.example.demo.model.InteractionRule;
import java.util.List;
import java.util.Optional;

public interface RuleService {
    InteractionRule addRule(RuleRequest ruleRequest);
    InteractionRule addRule(InteractionRule rule);
    InteractionRule createRule(InteractionRule rule);
    Optional<InteractionRule> getRuleBetweenIngredients(Long ingredientId1, Long ingredientId2);
    List<InteractionRule> getAllRules();
    Optional<InteractionRule> getRuleById(Long id);
    InteractionRule updateRule(Long id, InteractionRule ruleDetails);
    void deleteRule(Long id);
    List<InteractionRule> getRulesByMedicationId(Long medicationId);
    List<InteractionRule> getRulesBySeverity(String severity);
    List<InteractionRule> getRulesByIngredientId(Long ingredientId);
}