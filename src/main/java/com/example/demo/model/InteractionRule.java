package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "interaction_rules", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ingredient_a_id", "ingredient_b_id"})
})
public class InteractionRule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "First ingredient is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_a_id", nullable = false)
    private ActiveIngredient ingredientA;
    
    @NotNull(message = "Second ingredient is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_b_id", nullable = false)
    private ActiveIngredient ingredientB;
    
    @NotBlank(message = "Severity is required")
    @Size(max = 50, message = "Severity must be at most 50 characters")
    @Column(nullable = false, length = 50)
    private String severity; // e.g., "HIGH", "MEDIUM", "LOW", "CONTRAINDICATED"
    
    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description must be at most 1000 characters")
    @Column(nullable = false, length = 1000)
    private String description;
    
    // Optional: Add recommendation field
    @Size(max = 500, message = "Recommendation must be at most 500 characters")
    @Column(length = 500)
    private String recommendation;
    
    // Optional: Add evidence/source field
    @Size(max = 500, message = "Evidence must be at most 500 characters")
    @Column(length = 500)
    private String evidence;
    
    // Optional: Add status field
    @Column(nullable = false)
    private Boolean active = true;
    
    // Timestamps
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructor to ensure ingredient order (optional helper method)
    public static InteractionRule create(ActiveIngredient ingredient1, ActiveIngredient ingredient2, 
                                       String severity, String description) {
        // Ensure consistent ordering (e.g., by ID) to avoid duplicate rules
        if (ingredient1.getId().compareTo(ingredient2.getId()) > 0) {
            // Swap to maintain consistent ordering
            ActiveIngredient temp = ingredient1;
            ingredient1 = ingredient2;
            ingredient2 = temp;
        }
        return new InteractionRule(ingredient1, ingredient2, severity, description);
    }
    
    // Constructors
    public InteractionRule() {}
    
    public InteractionRule(ActiveIngredient ingredientA, ActiveIngredient ingredientB, 
                          String severity, String description) {
        this.ingredientA = ingredientA;
        this.ingredientB = ingredientB;
        this.severity = severity;
        this.description = description;
    }
    
    // Helper method to check if interaction involves a specific ingredient
    public boolean involvesIngredient(ActiveIngredient ingredient) {
        return ingredientA.equals(ingredient) || ingredientB.equals(ingredient);
    }
    
    // Helper method to get the other ingredient
    public ActiveIngredient getOtherIngredient(ActiveIngredient ingredient) {
        if (ingredientA.equals(ingredient)) {
            return ingredientB;
        } else if (ingredientB.equals(ingredient)) {
            return ingredientA;
        }
        return null;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public ActiveIngredient getIngredientA() { return ingredientA; }
    public void setIngredientA(ActiveIngredient ingredientA) { this.ingredientA = ingredientA; }
    
    public ActiveIngredient getIngredientB() { return ingredientB; }
    public void setIngredientB(ActiveIngredient ingredientB) { this.ingredientB = ingredientB; }
    
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
    
    public String getEvidence() { return evidence; }
    public void setEvidence(String evidence) { this.evidence = evidence; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    // Lifecycle callbacks
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        // Ensure consistent ordering of ingredients
        ensureConsistentOrdering();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        ensureConsistentOrdering();
    }
    
    // Ensure ingredientA always has lower ID than ingredientB to prevent duplicates
    private void ensureConsistentOrdering() {
        if (ingredientA != null && ingredientB != null && 
            ingredientA.getId() != null && ingredientB.getId() != null) {
            if (ingredientA.getId().compareTo(ingredientB.getId()) > 0) {
                // Swap ingredients
                ActiveIngredient temp = ingredientA;
                ingredientA = ingredientB;
                ingredientB = temp;
            }
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        InteractionRule that = (InteractionRule) o;
        
        // Check if same pair of ingredients (order doesn't matter)
        return (ingredientA.equals(that.ingredientA) && ingredientB.equals(that.ingredientB)) ||
               (ingredientA.equals(that.ingredientB) && ingredientB.equals(that.ingredientA));
    }
    
    @Override
    public int hashCode() {
        // Use commutative hash code for unordered pair
        int hash1 = ingredientA != null ? ingredientA.hashCode() : 0;
        int hash2 = ingredientB != null ? ingredientB.hashCode() : 0;
        return hash1 ^ hash2; // XOR is commutative
    }
    
    @Override
    public String toString() {
        return "InteractionRule{" +
                "id=" + id +
                ", ingredientA=" + (ingredientA != null ? ingredientA.getName() : "null") +
                ", ingredientB=" + (ingredientB != null ? ingredientB.getName() : "null") +
                ", severity='" + severity + '\'' +
                ", active=" + active +
                '}';
    }
}