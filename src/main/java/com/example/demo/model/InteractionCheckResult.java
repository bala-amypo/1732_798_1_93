package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interaction_check_results")
public class InteractionCheckResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "medication_names", length = 2000)
    private String medicationNames;
    
    @Column(name = "medication_ids", length = 2000)
    private String medicationIds;
    
    @Column(columnDefinition = "TEXT")
    private String interactions;
    
    @Column(name = "total_interactions")
    private Integer totalInteractions;
    
    @Column(name = "critical_interactions")
    private Integer criticalInteractions;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "user_email")
    private String userEmail;
    
    @Column(name = "session_id", length = 100)
    private String sessionId;
    
    @Column(name = "checked_at")
    private LocalDateTime checkedAt;
    
    @Column(name = "has_interactions")
    private Boolean hasInteractions = false;
    
    @Column(name = "recommendations", columnDefinition = "TEXT")
    private String recommendations;
    
    @PrePersist
    protected void onCreate() {
        if (checkedAt == null) {
            checkedAt = LocalDateTime.now();
        }
        if (hasInteractions == null) {
            hasInteractions = (totalInteractions != null && totalInteractions > 0);
        }
    }
    
    public InteractionCheckResult() {}
    
    // ... ALL YOUR EXISTING InteractionCheckResult CODE FROM BEFORE ...
    // Keep all your constructors, getters, setters, helper methods
    // Make sure this file ONLY contains the InteractionCheckResult class
}