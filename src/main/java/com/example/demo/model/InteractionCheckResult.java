package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interaction_check_results")
public class InteractionCheckResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "medications")
    private String medications;
    
    @Column(name = "interactions", columnDefinition = "TEXT")
    private String interactions;
    
    @Column(name = "checked_at")
    private LocalDateTime checkedAt;
    
    // Constructors
    public InteractionCheckResult() {
    }
    
    public InteractionCheckResult(String medications, String interactions) {
        this.medications = medications;
        this.interactions = interactions;
        this.checkedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getMedications() {
        return medications;
    }
    
    public void setMedications(String medications) {
        this.medications = medications;
    }
    
    public String getInteractions() {
        return interactions;
    }
    
    public void setInteractions(String interactions) {
        this.interactions = interactions;
    }
    
    public LocalDateTime getCheckedAt() {
        return checkedAt;
    }
    
    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }
}