package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "active_ingredients", uniqueConstraints = {
    @UniqueConstraint(columnNames = "name")
})
public class ActiveIngredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(unique = true)
    private String name;
    
    public ActiveIngredient() {}
    
    public ActiveIngredient(String name) {
        this.name = name;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}