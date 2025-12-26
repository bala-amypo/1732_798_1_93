// package com.example.demo.service.impl;

// import com.example.demo.dto.RuleRequest;
// import com.example.demo.model.ActiveIngredient;
// import com.example.demo.model.InteractionRule;
// import com.example.demo.repository.ActiveIngredientRepository;
// import com.example.demo.repository.InteractionRuleRepository;
// import com.example.demo.service.RuleService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class RuleServiceImpl implements RuleService {
    
//     @Autowired
//     private InteractionRuleRepository ruleRepository;
    
//     @Autowired
//     private ActiveIngredientRepository ingredientRepository;
    
//     @Override
//     @Transactional
//     public InteractionRule addRule(RuleRequest ruleRequest) {
//         if (ruleRequest == null) {
//             throw new IllegalArgumentException("RuleRequest cannot be null");
//         }
        
//         // Validate severity
//         String severity = ruleRequest.getSeverity();
//         if (severity == null) {
//             throw new IllegalArgumentException("Severity cannot be null");
//         }
        
//         // Get ingredients
//         ActiveIngredient ingredientA = ingredientRepository.findById(ruleRequest.getIngredientAId())
//                 .orElseThrow(() -> new IllegalArgumentException("Ingredient A not found"));
        
//         ActiveIngredient ingredientB = ingredientRepository.findById(ruleRequest.getIngredientBId())
//                 .orElseThrow(() -> new IllegalArgumentException("Ingredient B not found"));
        
//         // Create new rule
//         InteractionRule rule = new InteractionRule();
//         rule.setIngredientA(ingredientA);
//         rule.setIngredientB(ingredientB);
//         rule.setSeverity(severity.toUpperCase());  // Set severity
//         rule.setDescription(ruleRequest.getDescription());  // Set description
//         rule.setInteractionType(ruleRequest.getInteractionType());
//         rule.setRecommendation(ruleRequest.getRecommendation());
        
//         return ruleRepository.save(rule);
//     }
    
//     @Override
//     public Optional<InteractionRule> getRuleBetweenIngredients(Long ingredientId1, Long ingredientId2) {
//         return ruleRepository.findRuleBetweenIngredients(ingredientId1, ingredientId2);
//     }
    
//     @Override
//     @Transactional
//     public InteractionRule addRule(InteractionRule rule) {
//         // Validate severity
//         if (rule.getSeverity() != null) {
//             String severity = rule.getSeverity().toUpperCase();
//             if (!severity.equals("MINOR") && !severity.equals("MODERATE") && !severity.equals("MAJOR")) {
//                 throw new IllegalArgumentException("Severity must be MINOR, MODERATE, or MAJOR");
//             }
//             rule.setSeverity(severity);
//         }
//         return ruleRepository.save(rule);
//     }
    
//     @Override
//     @Transactional
//     public InteractionRule createRule(InteractionRule rule) {
//         return addRule(rule);
//     }
    
//     @Override
//     public List<InteractionRule> getAllRules() {
//         return ruleRepository.findAll();
//     }
    
//     @Override
//     public Optional<InteractionRule> getRuleById(Long id) {
//         return ruleRepository.findById(id);
//     }
    
//     @Override
//     @Transactional
//     public InteractionRule updateRule(Long id, InteractionRule ruleDetails) {
//         InteractionRule rule = ruleRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Rule not found with id: " + id));
        
//         if (ruleDetails.getIngredientA() != null) {
//             rule.setIngredientA(ruleDetails.getIngredientA());
//         }
//         if (ruleDetails.getIngredientB() != null) {
//             rule.setIngredientB(ruleDetails.getIngredientB());
//         }
//         if (ruleDetails.getSeverity() != null) {
//             rule.setSeverity(ruleDetails.getSeverity());
//         }
//         if (ruleDetails.getInteractionType() != null) {
//             rule.setInteractionType(ruleDetails.getInteractionType());
//         }
//         if (ruleDetails.getDescription() != null) {
//             rule.setDescription(ruleDetails.getDescription());
//         }
//         if (ruleDetails.getRecommendation() != null) {
//             rule.setRecommendation(ruleDetails.getRecommendation());
//         }
        
//         return ruleRepository.save(rule);
//     }
    
//     @Override
//     @Transactional
//     public void deleteRule(Long id) {
//         ruleRepository.deleteById(id);
//     }
    
//     @Override
//     public List<InteractionRule> getRulesByMedicationId(Long medicationId) {
//         return ruleRepository.findByMedicationId(medicationId);
//     }
    
//     @Override
//     public List<InteractionRule> getRulesBySeverity(String severity) {
//         return ruleRepository.findBySeverityIgnoreCase(severity);
//     }
    
//     @Override
//     public List<InteractionRule> getRulesByIngredientId(Long ingredientId) {
//         return ruleRepository.findByIngredientId(ingredientId);
//     }
// }

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
@Transactional
public class RuleServiceImpl implements RuleService {
    
    @Autowired
    private InteractionRuleRepository interactionRuleRepository;
    
    @Autowired
    private ActiveIngredientRepository activeIngredientRepository;
    
    @Override
    public InteractionRule createRule(InteractionRule rule) {
        // Cascade will automatically save new ingredients
        return interactionRuleRepository.save(rule);
    }
    
    @Override
    public InteractionRule addRule(InteractionRule rule) {
        return createRule(rule);
    }
    
    @Override
    public InteractionRule addRule(RuleRequest ruleRequest) {
        // Convert RuleRequest to InteractionRule
        InteractionRule rule = new InteractionRule();
        // ... mapping logic
        return createRule(rule);
    }
    
    // ... implement other methods from interface
}