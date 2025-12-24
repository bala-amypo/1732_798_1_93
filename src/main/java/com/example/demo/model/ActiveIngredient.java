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
    
    @Column(unique = true, nullable = false)
    private String name;
    
    @ManyToMany(mappedBy = "ingredients")
    private Set<Medication> medications = new HashSet<>();
    
    // Constructors
    public ActiveIngredient() {}
    
    public ActiveIngredient(String name) {
        this.name = name;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Set<Medication> getMedications() { return medications; }
    public void setMedications(Set<Medication> medications) { this.medications = medications; }
}