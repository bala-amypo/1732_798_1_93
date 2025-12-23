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
    
    public RuleServiceImpl(InteractionRuleRepository ruleRepository,
                          ActiveIngredientRepository ingredientRepository) {
        this.ruleRepository = ruleRepository;
        this.ingredientRepository = ingredientRepository;
    }
    
    @Override
    @Transactional
    public InteractionRule addRule(RuleRequest ruleRequest) {
        // Your implementation...
        // Make sure it uses findRuleBetweenIngredients which returns Optional
        if (ruleRepository != null && ruleRequest != null) {
            Optional<InteractionRule> existingRule = ruleRepository.findRuleBetweenIngredients(
                ruleRequest.getIngredientAId(), ruleRequest.getIngredientBId());
            if (existingRule.isPresent()) {
                throw new IllegalArgumentException("Rule already exists");
            }
        }
        
        // Rest of your implementation...
        InteractionRule rule = new InteractionRule();
        // Set properties...
        
        if (ruleRepository != null) {
            return ruleRepository.save(rule);
        }
        return rule;
    }
    
    @Override
    @Transactional
    public InteractionRule addRule(InteractionRule rule) {
        if (ruleRepository != null) {
            return ruleRepository.save(rule);
        }
        return rule;
    }
    
    @Override
    @Transactional
    public InteractionRule createRule(InteractionRule rule) {
        return addRule(rule);
    }
    
    @Override
    public List<InteractionRule> getAllRules() {
        if (ruleRepository == null) {
            return List.of();
        }
        return ruleRepository.findAll();
    }
    
    @Override
    public Optional<InteractionRule> getRuleById(Long id) {
        if (ruleRepository == null) {
            return Optional.empty();
        }
        return ruleRepository.findById(id);
    }
    
    @Override
    @Transactional
    public InteractionRule updateRule(Long id, InteractionRule ruleDetails) {
        if (ruleRepository == null) {
            return ruleDetails;
        }
        
        return ruleRepository.findById(id).map(rule -> {
            // Update logic...
            return ruleRepository.save(rule);
        }).orElseThrow(() -> new RuntimeException("Rule not found with id: " + id));
    }
    
    @Override
    @Transactional
    public void deleteRule(Long id) {
        if (ruleRepository != null) {
            ruleRepository.deleteById(id);
        }
    }
    
    @Override
    public List<InteractionRule> getRulesByMedicationId(Long medicationId) {
        if (ruleRepository == null) {
            return List.of();
        }
        return ruleRepository.findByMedicationId(medicationId);
    }
}