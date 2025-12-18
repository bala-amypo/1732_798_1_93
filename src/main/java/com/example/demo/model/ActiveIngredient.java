package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "active_ingredients", uniqueConstraints = {
    @UniqueConstraint(columnNames = "name")
})
public class ActiveIngredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Ingredient name is required")
    @Size(min = 2, max = 100, message = "Ingredient name must be between 2 and 100 characters")
    @Column(unique = true, nullable = false, length = 100)
    private String name;
    
    // Optional: Add description field
    @Column(columnDefinition = "TEXT")
    private String description;
    
    // Optional: Add scientific name field
    @Column(name = "scientific_name")
    private String scientificName;
    
    // Optional: Add status field
    @Column(nullable = false)
    private Boolean active = true;
    
    // Optional: Timestamps
    @Column(name = "created_at", updatable = false)
    private java.time.LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;
    
    // Constructors
    public ActiveIngredient() {}
    
    public ActiveIngredient(String name) {
        this.name = name;
    }
    
    public ActiveIngredient(String name, String description, String scientificName) {
        this.name = name;
        this.description = description;
        this.scientificName = scientificName;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getScientificName() { return scientificName; }
    public void setScientificName(String scientificName) { this.scientificName = scientificName; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public java.time.LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(java.time.LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public java.time.LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(java.time.LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    // Lifecycle callbacks (optional)
    @PrePersist
    protected void onCreate() {
        createdAt = java.time.LocalDateTime.now();
        updatedAt = java.time.LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = java.time.LocalDateTime.now();
    }
    
    // Override toString() for better logging
    @Override
    public String toString() {
        return "ActiveIngredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", active=" + active +
                '}';
    }
}