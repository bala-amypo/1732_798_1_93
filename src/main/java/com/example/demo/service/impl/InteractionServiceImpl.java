package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class InteractionCheckResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String medicationNames;
    private String interactions;
    private int totalInteractions;
    private int criticalInteractions;
    private boolean hasInteractions;
    
    // Add this field
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkDate; // Or use LocalDateTime if you prefer
    
    // Constructors
    public InteractionCheckResult() {
        this.checkDate = new Date(); // Set default date
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getMedicationNames() {
        return medicationNames;
    }
    
    public void setMedicationNames(String medicationNames) {
        this.medicationNames = medicationNames;
    }
    
    public String getInteractions() {
        return interactions;
    }
    
    public void setInteractions(String interactions) {
        this.interactions = interactions;
    }
    
    public int getTotalInteractions() {
        return totalInteractions;
    }
    
    public void setTotalInteractions(int totalInteractions) {
        this.totalInteractions = totalInteractions;
    }
    
    public int getCriticalInteractions() {
        return criticalInteractions;
    }
    
    public void setCriticalInteractions(int criticalInteractions) {
        this.criticalInteractions = criticalInteractions;
    }
    
    public boolean isHasInteractions() {
        return hasInteractions;
    }
    
    public void setHasInteractions(boolean hasInteractions) {
        this.hasInteractions = hasInteractions;
    }
    
    // ADD THIS METHOD:
    public Date getCheckDate() {
        return checkDate;
    }
    
    // ADD THIS METHOD:
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
}