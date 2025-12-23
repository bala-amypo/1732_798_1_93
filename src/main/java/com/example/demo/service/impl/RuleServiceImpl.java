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
import java.util.Optional;

@Service
public class RuleServiceImpl implements RuleService {
    
    @Autowired
    private InteractionRuleRepository ruleRepository;
    
    @Autowired
    private ActiveIngredientRepository ingredientRepository;
    
    // NO-ARG CONSTRUCTOR FOR TESTING
    public RuleServiceImpl() {
    }
    
    // Constructor for dependency injection in tests
    public RuleServiceImpl(InteractionRuleRepository ruleRepository,
                          ActiveIngredientRepository ingredientRepository) {
        this.ruleRepository = ruleRepository;
        this.ingredientRepository = ingredientRepository;
    }
    
    // REQUIRED: Add rule with RuleRequest
    @Override
    @Transactional
    public InteractionRule addRule(RuleRequest ruleRequest) {
        if (ruleRequest == null) {
            throw new IllegalArgumentException("RuleRequest cannot be null");
        }
        
        // Validate that ingredient IDs are provided
        if (ruleRequest.getIngredientAId() == null || ruleRequest.getIngredientBId() == null) {
            throw new IllegalArgumentException("Both ingredient IDs must be provided");
        }
        
        // Check if rule already exists (bidirectional check)
        Optional<InteractionRule> existingRule = ruleRepository.findRuleBetweenIngredients(
            ruleRequest.getIngredientAId(), ruleRequest.getIngredientBId());
        
        if (existingRule.isPresent()) {
            throw new IllegalArgumentException("Interaction rule already exists for these ingredients");
        }
        
        // Get ingredients
        ActiveIngredient ingredientA = ingredientRepository.findById(ruleRequest.getIngredientAId())
                .orElseThrow(() -> new IllegalArgumentException("Ingredient A not found with id: " + ruleRequest.getIngredientAId()));
        
        ActiveIngredient ingredientB = ingredientRepository.findById(ruleRequest.getIngredientBId())
                .orElseThrow(() -> new IllegalArgumentException("Ingredient B not found with id: " + ruleRequest.getIngredientBId()));
        
        // Create new rule
        InteractionRule rule = new InteractionRule();
        rule.setIngredientA(ingredientA);
        rule.setIngredientB(ingredientB);
        rule.setInteractionType(ruleRequest.getInteractionType());
        
        // Set severity with validation
        if (ruleRequest.getSeverity() != null) {
            rule.setSeverity(ruleRequest.getSeverity().toUpperCase());
        }
        
        rule.setDescription(ruleRequest.getDescription());
        rule.setRecommendation(ruleRequest.getRecommendation());
        
        return ruleRepository.save(rule);
    }
    
    // REQUIRED: Get rule between ingredients (bidirectional)
    @Override
    public Optional<InteractionRule> getRuleBetweenIngredients(Long ingredientId1, Long ingredientId2) {
        return ruleRepository.findRuleBetweenIngredients(ingredientId1, ingredientId2);
    }
    
    // Add rule with InteractionRule object
    @Override
    @Transactional
    public InteractionRule addRule(InteractionRule rule) {
        if (rule == null) {
            throw new IllegalArgumentException("Rule cannot be null");
        }
        
        // Validate severity
        if (rule.getSeverity() != null) {
            String severity = rule.getSeverity().toUpperCase();
            if (!severity.equals("MINOR") && !severity.equals("MODERATE") && !severity.equals("MAJOR")) {
                throw new IllegalArgumentException("Severity must be MINOR, MODERATE, or MAJOR");
            }
            rule.setSeverity(severity);
        }
        
        // Check if rule already exists
        if (rule.getIngredientA() != null && rule.getIngredientB() != null) {
            Optional<InteractionRule> existingRule = ruleRepository.findRuleBetweenIngredients(
                rule.getIngredientA().getId(), rule.getIngredientB().getId());
            
            if (existingRule.isPresent() && !existingRule.get().getId().equals(rule.getId())) {
                throw new IllegalArgumentException("Interaction rule already exists for these ingredients");
            }
        }
        
        return ruleRepository.save(rule);
    }
    
    @Override
    @Transactional
    public InteractionRule createRule(InteractionRule rule) {
        return addRule(rule);
    }
    
    @Override
    public List<InteractionRule> getAllRules() {
        return ruleRepository.findAll();
    }
    
    @Override
    public Optional<InteractionRule> getRuleById(Long id) {
        return ruleRepository.findById(id);
    }
    
    @Override
    @Transactional
    public InteractionRule updateRule(Long id, InteractionRule ruleDetails) {
        InteractionRule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found with id: " + id));
        
        if (ruleDetails.getIngredientA() != null) {
            rule.setIngredientA(ruleDetails.getIngredientA());
        }
        if (ruleDetails.getIngredientB() != null) {
            rule.setIngredientB(ruleDetails.getIngredientB());
        }
        if (ruleDetails.getSeverity() != null) {
            rule.setSeverity(ruleDetails.getSeverity());
        }
        if (ruleDetails.getInteractionType() != null) {
            rule.setInteractionType(ruleDetails.getInteractionType());
        }
        if (ruleDetails.getDescription() != null) {
            rule.setDescription(ruleDetails.getDescription());
        }
        if (ruleDetails.getRecommendation() != null) {
            rule.setRecommendation(ruleDetails.getRecommendation());
        }
        
        return ruleRepository.save(rule);
    }
    
    @Override
    @Transactional
    public void deleteRule(Long id) {
        if (!ruleRepository.existsById(id)) {
            throw new RuntimeException("Rule not found with id: " + id);
        }
        ruleRepository.deleteById(id);
    }
    
    @Override
    public List<InteractionRule> getRulesByMedicationId(Long medicationId) {
        // This method should be implemented in your repository
        return ruleRepository.findByMedication1IdOrMedication2Id(medicationId, medicationId);
    }
    
    // Additional helpful methods
    @Override
    public List<InteractionRule> getRulesByIngredientId(Long ingredientId) {
        return ruleRepository.findByIngredientId(ingredientId);
    }
    
    @Override
    public List<InteractionRule> getRulesBySeverity(String severity) {
        return ruleRepository.findBySeverityIgnoreCase(severity);
    }
}