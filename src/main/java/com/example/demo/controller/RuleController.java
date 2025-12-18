package com.example.demo.controller;

import com.example.demo.model.InteractionRule;
import com.example.demo.service.RuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rules")
@Tag(name = "Interaction Rules", description = "Manage drug interaction rules")
@SecurityRequirement(name = "bearer-key")
public class RuleController {
    
    private final RuleService ruleService;
    
    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }
    
    @PostMapping("/")
    @Operation(summary = "Add a new interaction rule")
    public ResponseEntity<?> addRule(@RequestBody InteractionRule rule) {
        try {
            InteractionRule savedRule = ruleService.addRule(rule);
            return ResponseEntity.ok(savedRule);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @GetMapping("/")
    @Operation(summary = "Get all interaction rules")
    public ResponseEntity<List<InteractionRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}