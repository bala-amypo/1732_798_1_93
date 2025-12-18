package com.example.demo.controller;

import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.Medication;
import com.example.demo.service.CatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;  // Add this import

@RestController
@RequestMapping("/catalog")
@Tag(name = "Catalog", description = "Medication and ingredient catalog management")
@SecurityRequirement(name = "bearer-key")
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
    
    // rest of the code...
}