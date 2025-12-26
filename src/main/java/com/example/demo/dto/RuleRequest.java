// package com.example.demo.dto;

// public class RuleRequest {
//     private Long ingredientAId;
//     private Long ingredientBId;
//     private String interactionType;
//     private String severity;
//     private String description;
//     private String recommendation;
    
//     // Constructors
//     public RuleRequest() {}
    
//     public RuleRequest(Long ingredientAId, Long ingredientBId, String severity, String description) {
//         this.ingredientAId = ingredientAId;
//         this.ingredientBId = ingredientBId;
//         this.severity = severity;
//         this.description = description;
//     }
    
//     public RuleRequest(Long ingredientAId, Long ingredientBId, String interactionType, 
//                       String severity, String description, String recommendation) {
//         this.ingredientAId = ingredientAId;
//         this.ingredientBId = ingredientBId;
//         this.interactionType = interactionType;
//         this.severity = severity;
//         this.description = description;
//         this.recommendation = recommendation;
//     }
    
//     // Getters and Setters
//     public Long getIngredientAId() { return ingredientAId; }
//     public void setIngredientAId(Long ingredientAId) { this.ingredientAId = ingredientAId; }
    
//     public Long getIngredientBId() { return ingredientBId; }
//     public void setIngredientBId(Long ingredientBId) { this.ingredientBId = ingredientBId; }
    
//     public String getInteractionType() { return interactionType; }
//     public void setInteractionType(String interactionType) { this.interactionType = interactionType; }
    
//     public String getSeverity() { return severity; }
//     public void setSeverity(String severity) { this.severity = severity; }
    
//     public String getDescription() { return description; }
//     public void setDescription(String description) { this.description = description; }
    
//     public String getRecommendation() { return recommendation; }
//     public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
// }

package com.example.demo.dto;

public class RuleRequest {
    private Long ingredientAId;
    private Long ingredientBId;
    private String interactionType;
    private String severity;
    private String description;
    private String recommendation;
    private Boolean active = true;          // ADD THIS
    private Long medication1Id;             // ADD THIS
    private Long medication2Id;             // ADD THIS
    
    // Constructors - UPDATED
    public RuleRequest() {}
    
    public RuleRequest(Long ingredientAId, Long ingredientBId, String severity, String description) {
        this.ingredientAId = ingredientAId;
        this.ingredientBId = ingredientBId;
        this.severity = severity;
        this.description = description;
        this.active = true;  // ADD THIS
    }
    
    public RuleRequest(Long ingredientAId, Long ingredientBId, String interactionType, 
                      String severity, String description, String recommendation) {
        this.ingredientAId = ingredientAId;
        this.ingredientBId = ingredientBId;
        this.interactionType = interactionType;
        this.severity = severity;
        this.description = description;
        this.recommendation = recommendation;
        this.active = true;  // ADD THIS
    }
    
    // NEW CONSTRUCTOR with all fields
    public RuleRequest(Long ingredientAId, Long ingredientBId, String interactionType, 
                      String severity, String description, String recommendation,
                      Boolean active, Long medication1Id, Long medication2Id) {
        this.ingredientAId = ingredientAId;
        this.ingredientBId = ingredientBId;
        this.interactionType = interactionType;
        this.severity = severity;
        this.description = description;
        this.recommendation = recommendation;
        this.active = active != null ? active : true;
        this.medication1Id = medication1Id;
        this.medication2Id = medication2Id;
    }
    
    // Getters and Setters - ADD NEW ONES
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
    
    // ADD THESE NEW GETTERS AND SETTERS:
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active != null ? active : true; }
    
    public Long getMedication1Id() { return medication1Id; }
    public void setMedication1Id(Long medication1Id) { this.medication1Id = medication1Id; }
    
    public Long getMedication2Id() { return medication2Id; }
    public void setMedication2Id(Long medication2Id) { this.medication2Id = medication2Id; }
}