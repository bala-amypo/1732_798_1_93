package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medications")
public class Medication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String name;
    
    @ManyToMany
    @JoinTable(
        name = "medication_ingredients",
        joinColumns = @JoinColumn(name = "medication_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @Size(min = 1, message = "Medication must include at least one ingredient")
    private Set<ActiveIngredient> ingredients = new HashSet<>();
    
    public Medication() {}
    
    public Medication(String name) {
        this.name = name;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<ActiveIngredient> getIngredients() { return ingredients; }
    public void setIngredients(Set<ActiveIngredient> ingredients) { this.ingredients = ingredients; }
}