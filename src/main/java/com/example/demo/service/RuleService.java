package com.example.demo.service;

import com.example.demo.dto.RuleRequest;
import com.example.demo.model.InteractionRule;
import java.util.List;

public interface RuleService {
    InteractionRule addRule(RuleRequest ruleRequest);
    List<InteractionRule> getAllRules();
    InteractionRule getRuleById(Long id);
    void deleteRule(Long id);  // Make sure this method exists
}