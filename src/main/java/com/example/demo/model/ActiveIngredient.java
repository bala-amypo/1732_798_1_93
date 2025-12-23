package com.example.demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "active_ingredients")
public class ActiveIngredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(name = "chemical_name")
    private String chemicalName;
    
    @Column(length = 1000)
    private String description;
    
    @ManyToMany(mappedBy = "ingredients")
    private Set<Medication> medications = new HashSet<>();
    
    // Constructors
    public ActiveIngredient() {}
    
    // Add this constructor for test compatibility
    public ActiveIngredient(String name) {
        this.name = name;
        this.chemicalName = name;
        this.description = "";
    }
    
    public ActiveIngredient(String name, String chemicalName, String description) {
        this.name = name;
        this.chemicalName = chemicalName;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getChemicalName() { return chemicalName; }
    public void setChemicalName(String chemicalName) { this.chemicalName = chemicalName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Set<Medication> getMedications() { return medications; }
    public void setMedications(Set<Medication> medications) { this.medications = medications; }
}