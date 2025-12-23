package com.example.demo.service.impl;

import com.example.demo.dto.RuleRequest;
import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.InteractionRule;
import com.example.demo.repository.ActiveIngredientRepository;
import com.example.demo.repository.InteractionRuleRepository;
import com.example.demo.service.RuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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
    @Transactional
    public InteractionRule addRule(RuleRequest ruleRequest) {
        // Validate severity
        String severity = ruleRequest.getSeverity().toUpperCase();
        if (!severity.equals("MINOR") && !severity.equals("MODERATE") && !severity.equals("MAJOR")) {
            throw new IllegalArgumentException("Severity must be MINOR, MODERATE, or MAJOR");
        }
        
        // Get ingredients
        ActiveIngredient ingredientA = ingredientRepository.findById(ruleRequest.getIngredientAId())
                .orElseThrow(() -> new IllegalArgumentException("Ingredient A not found"));
        
        ActiveIngredient ingredientB = ingredientRepository.findById(ruleRequest.getIngredientBId())
                .orElseThrow(() -> new IllegalArgumentException("Ingredient B not found"));
        
        // Check if rule already exists
        if (ruleRepository.existsByIngredients(ingredientA.getId(), ingredientB.getId())) {
            throw new IllegalArgumentException("Interaction rule already exists for these ingredients");
        }
        
        // Create and save rule
        InteractionRule rule = new InteractionRule();
        rule.setIngredientA(ingredientA);
        rule.setIngredientB(ingredientB);
        rule.setSeverity(severity);
        rule.setDescription(ruleRequest.getDescription());
        rule.setActive(true);
        
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
}