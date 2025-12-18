package com.example.demo.controller;

import com.example.demo.model.InteractionCheckResult;
import com.example.demo.service.InteractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/interact")
@Tag(name = "Interaction Check", description = "Check medication interactions and view results")
@SecurityRequirement(name = "bearer-key")
public class InteractionController {
    
    private final InteractionService interactionService;
    
    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }
    
    @PostMapping("/check")
    @Operation(summary = "Check interactions for given medication IDs")
    public ResponseEntity<?> checkInteractions(@RequestBody Map<String, List<Long>> request) {
        try {
            List<Long> medicationIds = request.get("medicationIds");
            InteractionCheckResult result = interactionService.checkInteractions(medicationIds);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @GetMapping("/result/{id}")
    @Operation(summary = "Get interaction check result by ID")
    public ResponseEntity<?> getResult(@PathVariable Long id) {
        try {
            InteractionCheckResult result = interactionService.getResult(id);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}