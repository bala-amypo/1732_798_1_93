// package com.example.demo.model;

// import com.fasterxml.jackson.annotation.JsonIgnore;
// import jakarta.persistence.*;
// import java.util.HashSet;
// import java.util.Set;

// @Entity
// @Table(name = "active_ingredients")
// public class ActiveIngredient {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @Column(unique = true, nullable = false)
//     private String name;
    
//     @Column(name = "chemical_name")
//     private String chemicalName;
    
//     @Column(length = 1000)
//     private String description;
    
//     @ManyToMany(mappedBy = "ingredients")
//     @JsonIgnore  // ← ADD THIS LINE
//     private Set<Medication> medications = new HashSet<>();
    
//     // Constructors
//     public ActiveIngredient() {}
    
//     public ActiveIngredient(String name) {
//         this.name = name;
//     }
    
//     // Getters and Setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
    
//     public String getName() { return name; }
//     public void setName(String name) { this.name = name; }
    
//     public String getChemicalName() { return chemicalName; }
//     public void setChemicalName(String chemicalName) { this.chemicalName = chemicalName; }
    
//     public String getDescription() { return description; }
//     public void setDescription(String description) { this.description = description; }
    
//     public Set<Medication> getMedications() { return medications; }
//     public void setMedications(Set<Medication> medications) { this.medications = medications; }
// }

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    
    @Column(name = "chemical_name")
    private String chemicalName;
    
    @Column(length = 1000)
    private String description;
    
    // ADD THIS FIELD - Required by database
    @Column(name = "active", nullable = false)
    private Boolean active = true;  // Default value
    
    @ManyToMany(mappedBy = "ingredients")
    @JsonIgnore
    private Set<Medication> medications = new HashSet<>();
    
    // Constructors
    public ActiveIngredient() {
        this.active = true;  // Initialize in constructor too
    }
    
    public ActiveIngredient(String name) {
        this.name = name;
        this.active = true;  // Initialize in constructor
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
    
    // ADD GETTER AND SETTER for active field
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public Set<Medication> getMedications() { return medications; }
    public void setMedications(Set<Medication> medications) { this.medications = medications; }
}