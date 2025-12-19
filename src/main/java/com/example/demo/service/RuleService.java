package com.example.demo.service;

import com.example.demo.dto.RuleRequest;
import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.InteractionRule;
import com.example.demo.repository.ActiveIngredientRepository;
import com.example.demo.repository.InteractionRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleService {
    
    private final InteractionRuleRepository ruleRepository;
    private final ActiveIngredientRepository ingredientRepository;
    
    public RuleService(InteractionRuleRepository ruleRepository,
                      ActiveIngredientRepository ingredientRepository) {
        this.ruleRepository = ruleRepository;
        this.ingredientRepository = ingredientRepository;
    }
    
    public InteractionRule addRule(RuleRequest request) {
        // Check if rule already exists
        Optional<InteractionRule> existingRule = ruleRepository.findByIngredients(
            request.getIngredientAId(), request.getIngredientBId());
        
        if (existingRule.isPresent()) {
            throw new IllegalArgumentException("Rule already exists between these ingredients");
        }
        
        // Get ingredients
        ActiveIngredient ingredientA = ingredientRepository.findById(request.getIngredientAId())
            .orElseThrow(() -> new IllegalArgumentException("Ingredient A not found"));
        
        ActiveIngredient ingredientB = ingredientRepository.findById(request.getIngredientBId())
            .orElseThrow(() -> new IllegalArgumentException("Ingredient B not found"));
        
        // Create and save rule
        InteractionRule rule = new InteractionRule();
        rule.setIngredientA(ingredientA);
        rule.setIngredientB(ingredientB);
        rule.setSeverity(request.getSeverity());
        rule.setDescription(request.getDescription());
        
        return ruleRepository.save(rule);
    }
    
    public List<InteractionRule> getAllRules() {
        return ruleRepository.findAll();
    }
}