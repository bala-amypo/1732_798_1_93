package com.example.demo.service.impl;

import com.example.demo.dto.RuleRequest;
import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.InteractionRule;
import com.example.demo.repository.ActiveIngredientRepository;
import com.example.demo.repository.InteractionRuleRepository;
import com.example.demo.service.RuleService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RuleServiceImpl implements RuleService {
    
    private final InteractionRuleRepository ruleRepository;
    private final ActiveIngredientRepository ingredientRepository;
    
    public RuleServiceImpl(InteractionRuleRepository ruleRepository,
                          ActiveIngredientRepository ingredientRepository) {
        this.ruleRepository = ruleRepository;
        this.ingredientRepository = ingredientRepository;
    }
    
    @Override
    public InteractionRule addRule(RuleRequest request) {
        Optional<InteractionRule> existingRule1 = ruleRepository.findByIngredients(
            request.getIngredientAId(), request.getIngredientBId());
        
        Optional<InteractionRule> existingRule2 = ruleRepository.findByIngredients(
            request.getIngredientBId(), request.getIngredientAId());
        
        if (existingRule1.isPresent() || existingRule2.isPresent()) {
            throw new IllegalArgumentException("Rule already exists between these ingredients");
        }
        
        ActiveIngredient ingredientA = ingredientRepository.findById(request.getIngredientAId())
            .orElseThrow(() -> new IllegalArgumentException("Ingredient A not found"));
        
        ActiveIngredient ingredientB = ingredientRepository.findById(request.getIngredientBId())
            .orElseThrow(() -> new IllegalArgumentException("Ingredient B not found"));
        
        InteractionRule rule = new InteractionRule();
        rule.setIngredientA(ingredientA);
        rule.setIngredientB(ingredientB);
        rule.setSeverity(request.getSeverity().toUpperCase());
        rule.setDescription(request.getDescription());
        
        return ruleRepository.save(rule);
    }
    
    @Override
    public List<InteractionRule> getAllRules() {
        return ruleRepository.findAll();
    }
    
    @Override
    public InteractionRule getRuleById(Long id) {
        return ruleRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Rule not found with id: " + id));
    }
    
    @Override
    public void deleteRule(Long id) {
        ruleRepository.deleteById(id);
    }
    
    @Override
    public InteractionRule updateRule(Long id, RuleRequest request) {
        InteractionRule existingRule = getRuleById(id);
        
        if (request.getIngredientAId() != null && request.getIngredientBId() != null) {
            ActiveIngredient ingredientA = ingredientRepository.findById(request.getIngredientAId())
                .orElseThrow(() -> new IllegalArgumentException("Ingredient A not found"));
            
            ActiveIngredient ingredientB = ingredientRepository.findById(request.getIngredientBId())
                .orElseThrow(() -> new IllegalArgumentException("Ingredient B not found"));
            
            existingRule.setIngredientA(ingredientA);
            existingRule.setIngredientB(ingredientB);
        }
        
        if (request.getSeverity() != null) {
            existingRule.setSeverity(request.getSeverity().toUpperCase());
        }
        
        if (request.getDescription() != null) {
            existingRule.setDescription(request.getDescription());
        }
        
        return ruleRepository.save(existingRule);
    }
}