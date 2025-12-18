package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.time.LocalDateTime;

@Entity
@Table(name = "medications")
public class Medication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Medication name is required")
    @Size(min = 2, max = 200, message = "Medication name must be between 2 and 200 characters")
    @Column(nullable = false, length = 200)
    private String name;
    
    // Optional: Add dosage form field
    @Column(name = "dosage_form")
    private String dosageForm; // e.g., tablet, capsule, syrup, injection
    
    // Optional: Add strength field
    private String strength; // e.g., 500mg, 10mg/mL
    
    // Optional: Add manufacturer field
    private String manufacturer;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "medication_ingredients",
        joinColumns = @JoinColumn(name = "medication_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @NotNull(message = "Ingredients cannot be null")
    @Size(min = 1, message = "Medication must include at least one ingredient")
    private Set<ActiveIngredient> ingredients = new HashSet<>();
    
    // Optional: Add status field
    @Column(nullable = false)
    private Boolean active = true;
    
    // Optional: Timestamps
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public Medication() {}
    
    public Medication(String name) {
        this.name = name;
    }
    
    public Medication(String name, Set<ActiveIngredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }
    
    // Helper method to add ingredient
    public void addIngredient(ActiveIngredient ingredient) {
        this.ingredients.add(ingredient);
    }
    
    // Helper method to remove ingredient
    public void removeIngredient(ActiveIngredient ingredient) {
        this.ingredients.remove(ingredient);
    }
    
    // Helper method to check if contains ingredient
    public boolean containsIngredient(ActiveIngredient ingredient) {
        return this.ingredients.contains(ingredient);
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDosageForm() { return dosageForm; }
    public void setDosageForm(String dosageForm) { this.dosageForm = dosageForm; }
    
    public String getStrength() { return strength; }
    public void setStrength(String strength) { this.strength = strength; }
    
    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    
    public Set<ActiveIngredient> getIngredients() { return ingredients; }
    public void setIngredients(Set<ActiveIngredient> ingredients) { 
        this.ingredients = ingredients != null ? ingredients : new HashSet<>();
    }
    
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
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dosageForm='" + dosageForm + '\'' +
                ", strength='" + strength + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", active=" + active +
                ", ingredientCount=" + (ingredients != null ? ingredients.size() : 0) +
                '}';
    }
}