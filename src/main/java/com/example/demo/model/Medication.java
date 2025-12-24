package com.example.demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medications")
public class Medication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @ManyToMany
    @JoinTable(
        name = "medication_ingredients",
        joinColumns = @JoinColumn(name = "medication_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<ActiveIngredient> ingredients = new HashSet<>();
    
    // Constructors
    public Medication() {}
    
    public Medication(String name) {
        this.name = name;
    }
    
    // Helper methods
    public void addIngredient(ActiveIngredient ingredient) {
        this.ingredients.add(ingredient);
        ingredient.getMedications().add(this);
    }
    
    public void removeIngredient(ActiveIngredient ingredient) {
        this.ingredients.remove(ingredient);
        ingredient.getMedications().remove(this);
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Set<ActiveIngredient> getIngredients() { return ingredients; }
    public void setIngredients(Set<ActiveIngredient> ingredients) { this.ingredients = ingredients; }
}