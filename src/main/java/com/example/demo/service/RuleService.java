package com.example.demo.service;

import com.example.demo.dto.RuleRequest;
import com.example.demo.model.InteractionRule;
import java.util.List;

public interface RuleService {
    // The test expects addRule to return InteractionRule
    InteractionRule addRule(RuleRequest ruleRequest);
    
    // For backward compatibility, also support adding InteractionRule directly
    InteractionRule addRule(InteractionRule rule);
    
    List<InteractionRule> getAllRules();
    InteractionRule getRuleById(Long id);
    void deleteRule(Long id);
}