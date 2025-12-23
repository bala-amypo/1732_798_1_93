package com.example.demo.controller;

import com.example.demo.model.InteractionRule;
import com.example.demo.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rules")
public class RuleController {
    
    @Autowired
    private RuleService ruleService;
    
    @GetMapping
    public ResponseEntity<List<InteractionRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<InteractionRule> getRuleById(@PathVariable Long id) {
        Optional<InteractionRule> rule = ruleService.getRuleById(id);
        return rule.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<InteractionRule> createRule(@RequestBody InteractionRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<InteractionRule> updateRule(@PathVariable Long id, @RequestBody InteractionRule ruleDetails) {
        return ResponseEntity.ok(ruleService.updateRule(id, ruleDetails));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRule(@PathVariable Long id) {
        ruleService.deleteRule(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/medication/{medicationId}")
    public ResponseEntity<List<InteractionRule>> getRulesByMedication(@PathVariable Long medicationId) {
        return ResponseEntity.ok(ruleService.getRulesByMedicationId(medicationId));
    }
}