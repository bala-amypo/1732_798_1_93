package com.example.demo.service;

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
    
    public InteractionRule addRule(InteractionRule rule) {
        ActiveIngredient ingredientA = ingredientRepository.findById(rule.getIngredientA().getId())
                .orElseThrow(() -> new IllegalArgumentException("Ingredient A not found"));
        ActiveIngredient ingredientB = ingredientRepository.findById(rule.getIngredientB().getId())
                .orElseThrow(() -> new IllegalArgumentException("Ingredient B not found"));
        
        Optional<InteractionRule> existingRule = ruleRepository.findRuleBetweenIngredients(
            ingredientA.getId(), ingredientB.getId());
        
        if (existingRule.isPresent()) {
            throw new IllegalArgumentException("Interaction rule for this ingredient pairing already exists");
        }
        
        rule.setIngredientA(ingredientA);
        rule.setIngredientB(ingredientB);
        
        return ruleRepository.save(rule);
    }
    
    public List<InteractionRule> getAllRules() {
        return ruleRepository.findAll();
    }
}