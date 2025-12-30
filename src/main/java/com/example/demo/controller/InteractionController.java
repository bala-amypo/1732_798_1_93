package com.example.demo.controller;

import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.Medication;
import com.example.demo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalog")
@CrossOrigin(origins = "*")
public class InteractionController {
    
    @Autowired
    private CatalogService catalogService;
    
    // Medication endpoints
    @PostMapping("/medications")
    public ResponseEntity<Medication> createMedication(@RequestBody Medication medication) {
        Medication created = catalogService.createMedication(medication);  // Use createMedication
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/medications")
    public ResponseEntity<List<Medication>> getAllMedications() {
        List<Medication> medications = catalogService.getAllMedications();
        return ResponseEntity.ok(medications);
    }
    
    @GetMapping("/medications/search")
    public ResponseEntity<List<Medication>> searchMedications(@RequestParam(required = false) String query) {
        List<Medication> medications = catalogService.searchMedications(query);  // Use searchMedications
        return ResponseEntity.ok(medications);
    }
    
    @GetMapping("/medications/{id}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable Long id) {
        Optional<Medication> medication = catalogService.getMedicationById(id);
        return medication.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/medications/{id}")
    public ResponseEntity<Medication> updateMedication(@PathVariable Long id, @RequestBody Medication medicationDetails) {
        try {
            Medication updated = catalogService.updateMedication(id, medicationDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/medications/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable Long id) {
        catalogService.deleteMedication(id);
        return ResponseEntity.noContent().build();
    }
    
    // Ingredient endpoints
    @PostMapping("/ingredients")
    public ResponseEntity<ActiveIngredient> createIngredient(@RequestBody ActiveIngredient ingredient) {
        ActiveIngredient created = catalogService.createIngredient(ingredient);  // Use createIngredient
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/ingredients")
    public ResponseEntity<List<ActiveIngredient>> getAllIngredients() {
        List<ActiveIngredient> ingredients = catalogService.getAllIngredients();
        return ResponseEntity.ok(ingredients);
    }
    
    @GetMapping("/ingredients/{id}")
    public ResponseEntity<ActiveIngredient> getIngredientById(@PathVariable Long id) {
        Optional<ActiveIngredient> ingredient = catalogService.getIngredientById(id);
        return ingredient.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/ingredients/name/{name}")
    public ResponseEntity<ActiveIngredient> getIngredientByName(@PathVariable String name) {
        Optional<ActiveIngredient> ingredient = catalogService.getIngredientByName(name);
        return ingredient.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/ingredients/{id}")
    public ResponseEntity<ActiveIngredient> updateIngredient(@PathVariable Long id, @RequestBody ActiveIngredient ingredientDetails) {
        try {
            ActiveIngredient updated = catalogService.updateIngredient(id, ingredientDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        catalogService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }
    
    // Relationship endpoints
    @PostMapping("/medications/{medicationId}/ingredients/{ingredientId}")
    public ResponseEntity<Medication> addIngredientToMedication(
            @PathVariable Long medicationId, 
            @PathVariable Long ingredientId) {
        try {
            Medication updated = catalogService.addIngredientToMedication(medicationId, ingredientId);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/medications/{medicationId}/ingredients/{ingredientId}")
    public ResponseEntity<Medication> removeIngredientFromMedication(
            @PathVariable Long medicationId, 
            @PathVariable Long ingredientId) {
        try {
            Medication updated = catalogService.removeIngredientFromMedication(medicationId, ingredientId);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}