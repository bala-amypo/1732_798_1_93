package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "interaction_rules", uniqueConstraints = {
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
    
    @NotBlank
    private String severity;
    
    @NotBlank
    private String description;
    
    public InteractionRule() {}
    
    public InteractionRule(ActiveIngredient ingredientA, ActiveIngredient ingredientB, 
                          String severity, String description) {
        this.ingredientA = ingredientA;
        this.ingredientB = ingredientB;
        this.severity = severity;
        this.description = description;
    }
    
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
}