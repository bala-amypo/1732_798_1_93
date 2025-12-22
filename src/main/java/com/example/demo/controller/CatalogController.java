package com.example.demo.controller;

import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.Medication;
import com.example.demo.service.CatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/catalog")
@Tag(name = "Catalog", description = "Medication and ingredient catalog management")
// REMOVE THIS LINE:
// @SecurityRequirement(name = "bearerAuth")
public class CatalogController {
    
    private final CatalogService catalogService;
    
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }
    
    @PostMapping("/ingredient")
    @Operation(summary = "Add a new active ingredient")
    public ResponseEntity<?> addIngredient(@RequestBody ActiveIngredient ingredient) {
        try {
            ActiveIngredient savedIngredient = catalogService.addIngredient(ingredient);
            return ResponseEntity.ok(savedIngredient);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/medication")
    @Operation(summary = "Add a new medication")
    public ResponseEntity<?> addMedication(@RequestBody Medication medication) {
        try {
            Medication savedMedication = catalogService.addMedication(medication);
            return ResponseEntity.ok(savedMedication);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @GetMapping("/medications")
    @Operation(summary = "List all medications")
    public ResponseEntity<List<Medication>> getAllMedications() {
        List<Medication> medications = catalogService.getAllMedications();
        return ResponseEntity.ok(medications);
    }
    
    @GetMapping("/ingredients")
    @Operation(summary = "List all ingredients")
    public ResponseEntity<List<ActiveIngredient>> getAllIngredients() {
        List<ActiveIngredient> ingredients = catalogService.getAllIngredients();
        return ResponseEntity.ok(ingredients);
    }
}