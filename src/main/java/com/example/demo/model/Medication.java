package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "medications")
public class Medication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(name = "generic_name")
    private String genericName;
    
    @Column(length = 1000)
    private String description;
    
    @Column(name = "dosage_form")
    private String dosageForm;
    
    private String strength;
    
    // Relationships
    @ManyToMany
    @JoinTable(
        name = "medication_ingredients",
        joinColumns = @JoinColumn(name = "medication_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<ActiveIngredient> ingredients;  // Changed from activeIngredients to ingredients
    
    // Constructors
    public Medication() {}
    
    public Medication(String name, String genericName, String description) {
        this.name = name;
        this.genericName = genericName;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getGenericName() { return genericName; }
    public void setGenericName(String genericName) { this.genericName = genericName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getDosageForm() { return dosageForm; }
    public void setDosageForm(String dosageForm) { this.dosageForm = dosageForm; }
    
    public String getStrength() { return strength; }
    public void setStrength(String strength) { this.strength = strength; }
    
    public List<ActiveIngredient> getIngredients() { return ingredients; }  // Changed from getActiveIngredients
    public void setIngredients(List<ActiveIngredient> ingredients) {  // Changed from setActiveIngredients
        this.ingredients = ingredients; 
    }
}