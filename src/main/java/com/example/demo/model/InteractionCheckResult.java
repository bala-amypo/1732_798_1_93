package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interaction_check_results")
public class InteractionCheckResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 2000)
    private String medications;
    
    @Column(columnDefinition = "TEXT")
    private String interactions;
    
    private LocalDateTime checkedAt;
    
    @PrePersist
    protected void onCreate() {
        checkedAt = LocalDateTime.now();
    }
    
    public InteractionCheckResult() {}
    
    public InteractionCheckResult(String medications, String interactions) {
        this.medications = medications;
        this.interactions = interactions;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMedications() { return medications; }
    public void setMedications(String medications) { this.medications = medications; }
    public String getInteractions() { return interactions; }
    public void setInteractions(String interactions) { this.interactions = interactions; }
    public LocalDateTime getCheckedAt() { return checkedAt; }
    public void setCheckedAt(LocalDateTime checkedAt) { this.checkedAt = checkedAt; }
}