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
    
    @Column(name = "interaction_details")
    private String interactionDetails;
    
    @Column(name = "has_interaction")
    private boolean hasInteraction;
    
    private LocalDateTime checkedAt;
    
    // Constructors
    public InteractionCheckResult() {
        this.checkedAt = LocalDateTime.now();
    }
    
    // Add this constructor for test compatibility (2 parameters)
    public InteractionCheckResult(String medicationNames, String interactions) {
        this.medicationNames = medicationNames;
        this.interactionDetails = interactions;
        this.hasInteraction = true;
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
    
    // Add alias methods for test compatibility
    public String getInteractions() {
        return this.interactionDetails;
    }
    
    public void setInteractions(String interactions) {
        this.interactionDetails = interactions;
    }
    
    public boolean isHasInteraction() { return hasInteraction; }
    public void setHasInteraction(boolean hasInteraction) { this.hasInteraction = hasInteraction; }
    
    public LocalDateTime getCheckedAt() { return checkedAt; }
    public void setCheckedAt(LocalDateTime checkedAt) { this.checkedAt = checkedAt; }
}