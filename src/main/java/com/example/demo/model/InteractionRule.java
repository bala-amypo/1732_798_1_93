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
    
    // ADD CASCADE HERE - This is CRITICAL
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ingredient_a_id", nullable = false)
    private ActiveIngredient ingredientA;
    
    // ADD CASCADE HERE - This is CRITICAL
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ingredient_b_id", nullable = false)
    private ActiveIngredient ingredientB;
    
    @Column(name = "interaction_type")
    private String interactionType;
    
    @Column(name = "severity", nullable = false)
    private String severity;
    
    @Column(name = "description", length = 1000)
    private String description;
    
    @Column(name = "recommendation", length = 1000)
    private String recommendation;
    
    // ADD THIS FIELD - Required by database
    @Column(name = "active", nullable = false)
    private Boolean active = true;
    
    @Column(name = "medication1_id")
    private Long medication1Id;
    
    @Column(name = "medication2_id")
    private Long medication2Id;
    
    // Constructors - UPDATED to include active field
    public InteractionRule() {
        this.active = true;
    }
    
    public InteractionRule(ActiveIngredient ingredientA, ActiveIngredient ingredientB, 
                          String severity, String description) {
        this.ingredientA = ingredientA;
        this.ingredientB = ingredientB;
        setSeverity(severity);
        this.description = description != null ? description : "";
        this.interactionType = "";
        this.recommendation = "";
        this.active = true;  // ADD THIS
    }
    
    public InteractionRule(ActiveIngredient ingredientA, ActiveIngredient ingredientB, 
                          String severity, String description, String interactionType, String recommendation) {
        this.ingredientA = ingredientA;
        this.ingredientB = ingredientB;
        setSeverity(severity);
        this.description = description != null ? description : "";
        this.interactionType = interactionType != null ? interactionType : "";
        this.recommendation = recommendation != null ? recommendation : "";
        this.active = true;  // ADD THIS
    }
    
    // Getters and Setters - ADD active getter/setter
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
    
    // ADD THIS GETTER AND SETTER
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
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