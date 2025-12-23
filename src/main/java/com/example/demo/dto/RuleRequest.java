package com.example.demo.dto;

public class RuleRequest {
    private Long ingredientAId;
    private Long ingredientBId;
    private String interactionType;
    private String severity;  // This should contain "MAJOR", "MODERATE", etc.
    private String description;  // This should contain the description text
    
    // Add recommendation field if needed by tests
    private String recommendation;
    
    // Constructors
    public RuleRequest() {}
    
    public RuleRequest(Long ingredientAId, Long ingredientBId, String severity, String description) {
        this.ingredientAId = ingredientAId;
        this.ingredientBId = ingredientBId;
        this.severity = severity;
        this.description = description;
    }
    
    public RuleRequest(Long ingredientAId, Long ingredientBId, String interactionType, 
                      String severity, String description, String recommendation) {
        this.ingredientAId = ingredientAId;
        this.ingredientBId = ingredientBId;
        this.interactionType = interactionType;
        this.severity = severity;
        this.description = description;
        this.recommendation = recommendation;
    }
    
    // Getters and Setters
    public Long getIngredientAId() { return ingredientAId; }
    public void setIngredientAId(Long ingredientAId) { this.ingredientAId = ingredientAId; }
    
    public Long getIngredientBId() { return ingredientBId; }
    public void setIngredientBId(Long ingredientBId) { this.ingredientBId = ingredientBId; }
    
    public String getInteractionType() { return interactionType; }
    public void setInteractionType(String interactionType) { this.interactionType = interactionType; }
    
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
}