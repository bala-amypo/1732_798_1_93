package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interaction_check_results")
public class InteractionCheckResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "medication_names")
    private String medicationNames;
    
    @Column(name = "interactions_json", columnDefinition = "TEXT")
    private String interactionsJson;
    
    @Column(name = "checked_at")
    private LocalDateTime checkedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getMedicationNames() { return medicationNames; }
    public void setMedicationNames(String medicationNames) { this.medicationNames = medicationNames; }
    
    public String getInteractionsJson() { return interactionsJson; }
    public void setInteractionsJson(String interactionsJson) { this.interactionsJson = interactionsJson; }
    
    public LocalDateTime getCheckedAt() { return checkedAt; }
    public void setCheckedAt(LocalDateTime checkedAt) { this.checkedAt = checkedAt; }
}