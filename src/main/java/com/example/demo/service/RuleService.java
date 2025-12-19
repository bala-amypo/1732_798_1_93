package com.example.demo.service;

import com.example.demo.dto.RuleRequest;
import com.example.demo.model.InteractionRule;
import java.util.List;

public interface RuleService {
    InteractionRule addRule(RuleRequest request);
    List<InteractionRule> getAllRules();
    InteractionRule getRuleById(Long id);
    void deleteRule(Long id);
    InteractionRule updateRule(Long id, RuleRequest request);
}