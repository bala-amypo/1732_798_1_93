package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "interaction_rules", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"ingredient_a_id", "ingredient_b_id"})
       })
public class InteractionRule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "ingredient_a_id", nullable = false)
    private ActiveIngredient ingredientA;
    
    @ManyToOne
    @JoinColumn(name = "ingredient_b_id", nullable = false)
    private ActiveIngredient ingredientB;
    
    @Column(name = "interaction_type")
    private String interactionType;
    
    @Column(name = "severity", nullable = false)
    private String severity;  // Should be "MINOR", "MODERATE", "MAJOR"
    
    @Column(name = "description", length = 1000)
    private String description;
    
    @Column(name = "recommendation", length = 1000)
    private String recommendation;
    
    @Column(name = "medication1_id")
    private Long medication1Id;
    
    @Column(name = "medication2_id")
    private Long medication2Id;
    
    // Constructors
    public InteractionRule() {}
    
    public InteractionRule(ActiveIngredient ingredientA, ActiveIngredient ingredientB, 
                          String interactionType, String severity) {
        this.ingredientA = ingredientA;
        this.ingredientB = ingredientB;
        this.interactionType = interactionType;
        setSeverity(severity);
        this.description = "";
        this.recommendation = "";
    }
    
    public InteractionRule(ActiveIngredient ingredientA, ActiveIngredient ingredientB, 
                          String interactionType, String severity, String description, String recommendation) {
        this.ingredientA = ingredientA;
        this.ingredientB = ingredientB;
        this.interactionType = interactionType;
        setSeverity(severity);
        this.description = description;
        this.recommendation = recommendation;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getMedication1Id() { return medication1Id; }
    public void setMedication1Id(Long medication1Id) { this.medication1Id = medication1Id; }
    
    public Long getMedication2Id() { return medication2Id; }
    public void setMedication2Id(Long medication2Id) { this.medication2Id = medication2Id; }
    
    public ActiveIngredient getIngredientA() { return ingredientA; }
    public void setIngredientA(ActiveIngredient ingredientA) { this.ingredientA = ingredientA; }
    
    public ActiveIngredient getIngredientB() { return ingredientB; }
    public void setIngredientB(ActiveIngredient ingredientB) { this.ingredientB = ingredientB; }
    
    public String getInteractionType() { return interactionType; }
    public void setInteractionType(String interactionType) { this.interactionType = interactionType; }
    
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) {
        if (severity != null) {
            String upperSeverity = severity.toUpperCase();
            if (upperSeverity.equals("MINOR") || upperSeverity.equals("MODERATE") || upperSeverity.equals("MAJOR")) {
                this.severity = upperSeverity;
            } else {
                throw new IllegalArgumentException("Severity must be MINOR, MODERATE, or MAJOR");
            }
        }
    }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InteractionRule that = (InteractionRule) o;
        return Objects.equals(ingredientA.getId(), that.ingredientA.getId()) &&
               Objects.equals(ingredientB.getId(), that.ingredientB.getId()) ||
               Objects.equals(ingredientA.getId(), that.ingredientB.getId()) &&
               Objects.equals(ingredientB.getId(), that.ingredientA.getId());
    }
    
    @Override
    public int hashCode() {
        Long id1 = ingredientA != null ? ingredientA.getId() : null;
        Long id2 = ingredientB != null ? ingredientB.getId() : null;
        if (id1 == null && id2 == null) return 0;
        if (id1 == null) return id2.hashCode();
        if (id2 == null) return id1.hashCode();
        return id1.hashCode() ^ id2.hashCode();
    }
}