package com.example.demo.controller;

import com.example.demo.dto.RuleRequest;
import com.example.demo.model.InteractionRule;
import com.example.demo.service.RuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class RuleController {
    
    private final RuleService ruleService;
    
    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }
    
    @PostMapping
    public InteractionRule addRule(@RequestBody RuleRequest request) {
        return ruleService.addRule(request);
    }
    
    @GetMapping
    public List<InteractionRule> getAllRules() {
        return ruleService.getAllRules();
    }
}