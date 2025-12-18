package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.InteractionCheckResultRepository;
import com.example.demo.repository.InteractionRuleRepository;
import com.example.demo.repository.MedicationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InteractionService {
    
    private final MedicationRepository medicationRepository;
    private final InteractionRuleRepository ruleRepository;
    private final InteractionCheckResultRepository resultRepository;
    private final ObjectMapper objectMapper;
    
    public InteractionService(MedicationRepository medicationRepository,
                            InteractionRuleRepository ruleRepository,
                            InteractionCheckResultRepository resultRepository) {
        this.medicationRepository = medicationRepository;
        this.ruleRepository = ruleRepository;
        this.resultRepository = resultRepository;
        this.objectMapper = new ObjectMapper();
    }
    
    public InteractionCheckResult checkInteractions(List<Long> medicationIds) {
        if (medicationIds == null || medicationIds.isEmpty()) {
            throw new IllegalArgumentException("Medication list cannot be empty");
        }
        
        List<Medication> medications = medicationRepository.findAllById(medicationIds);
        if (medications.size() != medicationIds.size()) {
            throw new IllegalArgumentException("One or more medications not found");
        }
        
        Set<ActiveIngredient> allIngredients = new HashSet<>();
        for (Medication med : medications) {
            allIngredients.addAll(med.getIngredients());
        }
        
        List<InteractionRule> foundInteractions = new ArrayList<>();
        List<ActiveIngredient> ingredientList = new ArrayList<>(allIngredients);
        
        for (int i = 0; i < ingredientList.size(); i++) {
            for (int j = i + 1; j < ingredientList.size(); j++) {
                ActiveIngredient ing1 = ingredientList.get(i);
                ActiveIngredient ing2 = ingredientList.get(j);
                
                Optional<InteractionRule> rule = ruleRepository.findRuleBetweenIngredients(
                    ing1.getId(), ing2.getId());
                
                if (rule.isPresent()) {
                    foundInteractions.add(rule.get());
                }
            }
        }
        
        String medicationsStr = medications.stream()
            .map(Medication::getName)
            .collect(Collectors.joining(", "));
        
        String interactionsJson = buildInteractionsJson(foundInteractions);
        
        InteractionCheckResult result = new InteractionCheckResult(medicationsStr, interactionsJson);
        return resultRepository.save(result);
    }
    
    private String buildInteractionsJson(List<InteractionRule> interactions) {
        ArrayNode interactionsArray = objectMapper.createArrayNode();
        
        for (InteractionRule rule : interactions) {
            ObjectNode interactionNode = objectMapper.createObjectNode();
            interactionNode.put("ingredientA", rule.getIngredientA().getName());
            interactionNode.put("ingredientB", rule.getIngredientB().getName());
            interactionNode.put("severity", rule.getSeverity());
            interactionNode.put("description", rule.getDescription());
            interactionsArray.add(interactionNode);
        }
        
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("hasInteractions", !interactions.isEmpty());
        rootNode.set("interactions", interactionsArray);
        
        try {
            return objectMapper.writeValueAsString(rootNode);
        } catch (Exception e) {
            return "{\"hasInteractions\":false,\"interactions\":[]}";
        }
    }
    
    public InteractionCheckResult getResult(Long resultId) {
        return resultRepository.findById(resultId)
                .orElseThrow(() -> new IllegalArgumentException("Result not found"));
    }
}