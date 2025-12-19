package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RuleRequest {
    
    @NotNull(message = "Ingredient A ID is required")
    private Long ingredientAId;
    
    @NotNull(message = "Ingredient B ID is required")
    private Long ingredientBId;
    
    @NotBlank(message = "Severity is required")
    private String severity;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    // Getters and Setters
    public Long getIngredientAId() { return ingredientAId; }
    public void setIngredientAId(Long ingredientAId) { this.ingredientAId = ingredientAId; }
    
    public Long getIngredientBId() { return ingredientBId; }
    public void setIngredientBId(Long ingredientBId) { this.ingredientBId = ingredientBId; }
    
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}