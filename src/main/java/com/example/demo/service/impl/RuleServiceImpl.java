package com.example.demo.service.impl;

import com.example.demo.dto.RuleRequest;
import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.InteractionRule;
import com.example.demo.repository.ActiveIngredientRepository;
import com.example.demo.repository.InteractionRuleRepository;
import com.example.demo.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {
    
    private InteractionRuleRepository ruleRepository;
    private ActiveIngredientRepository ingredientRepository;
    
    // Add no-arg constructor
    public RuleServiceImpl() {
        // For testing
    }
    
    // Keep normal constructor
    @Autowired
    public RuleServiceImpl(InteractionRuleRepository ruleRepository,
                          ActiveIngredientRepository ingredientRepository) {
        this.ruleRepository = ruleRepository;
        this.ingredientRepository = ingredientRepository;
    }
    
    // Test expects addRule that returns InteractionRule, not void
    @Override
    @Transactional
    public InteractionRule addRule(RuleRequest ruleRequest) {
        if (ruleRepository == null || ingredientRepository == null) {
            throw new IllegalStateException("Repositories not initialized");
        }
        
        // Your implementation here...
        ActiveIngredient ingredientA = ingredientRepository.findById(ruleRequest.getIngredientAId())
                .orElseThrow(() -> new IllegalArgumentException("Ingredient A not found"));
        
        ActiveIngredient ingredientB = ingredientRepository.findById(ruleRequest.getIngredientBId())
                .orElseThrow(() -> new IllegalArgumentException("Ingredient B not found"));
        
        InteractionRule rule = new InteractionRule();
        rule.setIngredientA(ingredientA);
        rule.setIngredientB(ingredientB);
        rule.setSeverity(ruleRequest.getSeverity());
        rule.setDescription(ruleRequest.getDescription());
        
        return ruleRepository.save(rule);
    }
    
    // Also add method that accepts InteractionRule directly (for test)
    public InteractionRule addRule(InteractionRule rule) {
        if (ruleRepository == null) {
            throw new IllegalStateException("RuleRepository not initialized");
        }
        return ruleRepository.save(rule);
    }
    
    // Keep other methods...
}