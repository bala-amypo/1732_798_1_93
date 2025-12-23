package com.example.demo.service.impl;

import com.example.demo.model.InteractionRule;
import com.example.demo.repository.InteractionRuleRepository;
import com.example.demo.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleServiceImpl implements RuleService {
    
    @Autowired
    private InteractionRuleRepository ruleRepository;
    
    @Override
    public List<InteractionRule> getAllRules() {
        return ruleRepository.findAll();
    }
    
    @Override
    public Optional<InteractionRule> getRuleById(Long id) {
        return ruleRepository.findById(id);
    }
    
    @Override
    public InteractionRule createRule(InteractionRule rule) {
        return ruleRepository.save(rule);
    }
    
    @Override
    public InteractionRule addRule(InteractionRule rule) {
        return createRule(rule); // Alias
    }
    
    @Override
    public InteractionRule updateRule(Long id, InteractionRule ruleDetails) {
        return ruleRepository.findById(id).map(rule -> {
            if (ruleDetails.getMedication1Id() != null) {
                rule.setMedication1Id(ruleDetails.getMedication1Id());
            }
            if (ruleDetails.getMedication2Id() != null) {
                rule.setMedication2Id(ruleDetails.getMedication2Id());
            }
            if (ruleDetails.getIngredientA() != null) {
                rule.setIngredientA(ruleDetails.getIngredientA());
            }
            if (ruleDetails.getIngredientB() != null) {
                rule.setIngredientB(ruleDetails.getIngredientB());
            }
            if (ruleDetails.getInteractionType() != null) {
                rule.setInteractionType(ruleDetails.getInteractionType());
            }
            if (ruleDetails.getSeverity() != null) {
                rule.setSeverity(ruleDetails.getSeverity());
            }
            if (ruleDetails.getDescription() != null) {
                rule.setDescription(ruleDetails.getDescription());
            }
            if (ruleDetails.getRecommendation() != null) {
                rule.setRecommendation(ruleDetails.getRecommendation());
            }
            return ruleRepository.save(rule);
        }).orElseThrow(() -> new RuntimeException("Rule not found with id: " + id));
    }
    
    @Override
    public void deleteRule(Long id) {
        ruleRepository.deleteById(id);
    }
    
    @Override
    public List<InteractionRule> getRulesByMedicationId(Long medicationId) {
        return ruleRepository.findByMedicationId(medicationId);
    }
    
    @Override
    public List<InteractionRule> getRuleBetweenIngredients(Long ingredientId1, Long ingredientId2) {
        // Now this correctly returns List<InteractionRule>
        return ruleRepository.findRuleBetweenIngredients(ingredientId1, ingredientId2);
    }
}