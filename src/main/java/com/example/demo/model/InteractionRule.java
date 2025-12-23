package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "interaction_rules")
public class InteractionRule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "medication1_id")
    private Long medication1Id;
    
    @Column(name = "medication2_id")
    private Long medication2Id;
    
    // Add ingredient fields
    @ManyToOne
    @JoinColumn(name = "ingredient_a_id")
    private ActiveIngredient ingredientA;
    
    @ManyToOne
    @JoinColumn(name = "ingredient_b_id")
    private ActiveIngredient ingredientB;
    
    @Column(name = "interaction_type")
    private String interactionType;
    
    @Column(name = "severity")
    private String severity; // HIGH, MEDIUM, LOW
    
    @Column(name = "description", length = 1000)
    private String description;
    
    @Column(name = "recommendation", length = 1000)
    private String recommendation;
    
    // Constructors
    public InteractionRule() {}
    
    // Constructor with ingredients
    public InteractionRule(ActiveIngredient ingredientA, ActiveIngredient ingredientB, 
                          String interactionType, String severity, String description, String recommendation) {
        this.ingredientA = ingredientA;
        this.ingredientB = ingredientB;
        this.interactionType = interactionType;
        this.severity = severity;
        this.description = description;
        this.recommendation = recommendation;
    }
    
    // Constructor with medication IDs
    public InteractionRule(Long medication1Id, Long medication2Id, String interactionType, 
                          String severity, String description, String recommendation) {
        this.medication1Id = medication1Id;
        this.medication2Id = medication2Id;
        this.interactionType = interactionType;
        this.severity = severity;
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
    public void setSeverity(String severity) { this.severity = severity; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
}