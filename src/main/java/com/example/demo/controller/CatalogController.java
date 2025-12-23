package com.example.demo.controller;

import com.example.demo.model.Medication;
import com.example.demo.model.ActiveIngredient;
import com.example.demo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {
    
    @Autowired
    private CatalogService catalogService;
    
    // Medication endpoints
    
    @GetMapping("/medications")
    public ResponseEntity<List<Medication>> getAllMedications() {
        return ResponseEntity.ok(catalogService.getAllMedications());
    }
    
    @GetMapping("/medications/{id}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable Long id) {
        Optional<Medication> medication = catalogService.getMedicationById(id);
        return medication.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/medications")
    public ResponseEntity<Medication> createMedication(@RequestBody Medication medication) {
        return ResponseEntity.ok(catalogService.createMedication(medication));
    }
    
    @PutMapping("/medications/{id}")
    public ResponseEntity<Medication> updateMedication(@PathVariable Long id, 
                                                       @RequestBody Medication medicationDetails) {
        return ResponseEntity.ok(catalogService.updateMedication(id, medicationDetails));
    }
    
    @DeleteMapping("/medications/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable Long id) {
        catalogService.deleteMedication(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/medications/search")
    public ResponseEntity<List<Medication>> searchMedications(@RequestParam String keyword) {
        return ResponseEntity.ok(catalogService.searchMedications(keyword));
    }
    
    // ActiveIngredient endpoints (if CatalogService has these methods)
    
    @GetMapping("/ingredients")
    public ResponseEntity<List<ActiveIngredient>> getAllIngredients() {
        // If you don't have this method in CatalogService, you'll need to:
        // 1. Add it to CatalogService interface, OR
        // 2. Create a separate ActiveIngredientController
        return ResponseEntity.ok(catalogService.getAllIngredients());
    }
    
    @GetMapping("/ingredients/{id}")
    public ResponseEntity<ActiveIngredient> getIngredientById(@PathVariable Long id) {
        Optional<ActiveIngredient> ingredient = catalogService.getIngredientById(id);
        return ingredient.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/ingredients")
    public ResponseEntity<ActiveIngredient> createIngredient(@RequestBody ActiveIngredient ingredient) {
        return ResponseEntity.ok(catalogService.createIngredient(ingredient));
    }
    
    @PutMapping("/ingredients/{id}")
    public ResponseEntity<ActiveIngredient> updateIngredient(@PathVariable Long id, 
                                                            @RequestBody ActiveIngredient ingredientDetails) {
        return ResponseEntity.ok(catalogService.updateIngredient(id, ingredientDetails));
    }
    
    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        catalogService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }
}