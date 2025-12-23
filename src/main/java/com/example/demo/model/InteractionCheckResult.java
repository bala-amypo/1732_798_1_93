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
    private String medicationNames;  // Changed from medications
    
    @Column(name = "interaction_details")
    private String interactionDetails;  // Changed from interactions
    
    @Column(name = "has_interaction")
    private boolean hasInteraction;
    
    private LocalDateTime checkedAt;
    
    // Constructors
    public InteractionCheckResult() {
        this.checkedAt = LocalDateTime.now();
    }
    
    public InteractionCheckResult(String medicationNames, String interactionDetails, boolean hasInteraction) {
        this.medicationNames = medicationNames;
        this.interactionDetails = interactionDetails;
        this.hasInteraction = hasInteraction;
        this.checkedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getMedicationNames() { return medicationNames; }
    public void setMedicationNames(String medicationNames) { this.medicationNames = medicationNames; }
    
    public String getInteractionDetails() { return interactionDetails; }
    public void setInteractionDetails(String interactionDetails) { this.interactionDetails = interactionDetails; }
    
    public boolean isHasInteraction() { return hasInteraction; }
    public void setHasInteraction(boolean hasInteraction) { this.hasInteraction = hasInteraction; }
    
    public LocalDateTime getCheckedAt() { return checkedAt; }
    public void setCheckedAt(LocalDateTime checkedAt) { this.checkedAt = checkedAt; }
}