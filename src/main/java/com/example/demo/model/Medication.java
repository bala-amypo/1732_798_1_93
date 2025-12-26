// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.util.HashSet;
// import java.util.Set;

// @Entity
// @Table(name = "medications")
// public class Medication {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @Column(nullable = false)
//     private String name;
    
//     @Column(name = "generic_name")
//     private String genericName;
    
//     @Column(length = 1000)
//     private String description;
    
//     @Column(name = "dosage_form")
//     private String dosageForm;
    
//     private String strength;
    
//     @ManyToMany
//     @JoinTable(
//         name = "medication_ingredients",
//         joinColumns = @JoinColumn(name = "medication_id"),
//         inverseJoinColumns = @JoinColumn(name = "ingredient_id")
//     )
//     private Set<ActiveIngredient> ingredients = new HashSet<>();
    
//     // Constructors
//     public Medication() {}
    
//     public Medication(String name) {
//         this.name = name;
//     }
    
//     // Helper methods
//     public void addIngredient(ActiveIngredient ingredient) {
//         this.ingredients.add(ingredient);
//         ingredient.getMedications().add(this);
//     }
    
//     public void removeIngredient(ActiveIngredient ingredient) {
//         this.ingredients.remove(ingredient);
//         ingredient.getMedications().remove(this);
//     }
    
//     // Getters and Setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
    
//     public String getName() { return name; }
//     public void setName(String name) { this.name = name; }
    
//     public String getGenericName() { return genericName; }
//     public void setGenericName(String genericName) { this.genericName = genericName; }
    
//     public String getDescription() { return description; }
//     public void setDescription(String description) { this.description = description; }
    
//     public String getDosageForm() { return dosageForm; }
//     public void setDosageForm(String dosageForm) { this.dosageForm = dosageForm; }
    
//     public String getStrength() { return strength; }
//     public void setStrength(String strength) { this.strength = strength; }
    
//     public Set<ActiveIngredient> getIngredients() { return ingredients; }
//     public void setIngredients(Set<ActiveIngredient> ingredients) { this.ingredients = ingredients; }
// }

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
    
    @Column(name = "generic_name")
    private String genericName;
    
    @Column(length = 1000)
    private String description;
    
    @Column(name = "dosage_form")
    private String dosageForm;
    
    private String strength;
    
    // ADD THIS FIELD - Required by database
    @Column(name = "active", nullable = false)
    private Boolean active = true;  // Default value
    
    @ManyToMany
    @JoinTable(
        name = "medication_ingredients",
        joinColumns = @JoinColumn(name = "medication_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<ActiveIngredient> ingredients = new HashSet<>();
    
    // Constructors
    public Medication() {
        this.active = true;  // Initialize in constructor
    }
    
    public Medication(String name) {
        this.name = name;
        this.active = true;  // Initialize in constructor
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
    
    public String getGenericName() { return genericName; }
    public void setGenericName(String genericName) { this.genericName = genericName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getDosageForm() { return dosageForm; }
    public void setDosageForm(String dosageForm) { this.dosageForm = dosageForm; }
    
    public String getStrength() { return strength; }
    public void setStrength(String strength) { this.strength = strength; }
    
    // ADD GETTER AND SETTER for active field
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public Set<ActiveIngredient> getIngredients() { return ingredients; }
    public void setIngredients(Set<ActiveIngredient> ingredients) { this.ingredients = ingredients; }
}