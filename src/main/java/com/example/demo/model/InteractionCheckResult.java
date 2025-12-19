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
    
    public InteractionCheckResult(String medicationNames, String interactions) {
        this.medicationNames = medicationNames;
        this.interactions = interactions;
        this.hasInteractions = interactions != null && !interactions.isEmpty();
    }
    
    public InteractionCheckResult(String medicationNames, String medicationIds, 
                                String interactions, Integer totalInteractions, 
                                Integer criticalInteractions, Long userId, 
                                String userEmail) {
        this.medicationNames = medicationNames;
        this.medicationIds = medicationIds;
        this.interactions = interactions;
        this.totalInteractions = totalInteractions;
        this.criticalInteractions = criticalInteractions;
        this.userId = userId;
        this.userEmail = userEmail;
        this.hasInteractions = totalInteractions != null && totalInteractions > 0;
    }
    
    // ========== GETTERS ==========
    public Long getId() { 
        return id; 
    }
    
    public String getMedicationNames() { 
        return medicationNames; 
    }
    
    public String getMedicationIds() { 
        return medicationIds; 
    }
    
    public String getInteractions() { 
        return interactions; 
    }
    
    public Integer getTotalInteractions() { 
        return totalInteractions; 
    }
    
    public Integer getCriticalInteractions() { 
        return criticalInteractions; 
    }
    
    public Long getUserId() { 
        return userId; 
    }
    
    public String getUserEmail() { 
        return userEmail; 
    }
    
    public String getSessionId() { 
        return sessionId; 
    }
    
    public LocalDateTime getCheckedAt() { 
        return checkedAt; 
    }
    
    public Boolean getHasInteractions() { 
        return hasInteractions; 
    }
    
    public String getRecommendations() { 
        return recommendations; 
    }
    
    // ========== SETTERS ==========
    public void setId(Long id) { 
        this.id = id; 
    }
    
    public void setMedicationNames(String medicationNames) { 
        this.medicationNames = medicationNames; 
    }
    
    public void setMedicationIds(String medicationIds) { 
        this.medicationIds = medicationIds; 
    }
    
    public void setInteractions(String interactions) { 
        this.interactions = interactions; 
        if (interactions != null) {
            this.hasInteractions = !interactions.isEmpty();
        }
    }
    
    public void setTotalInteractions(Integer totalInteractions) { 
        this.totalInteractions = totalInteractions; 
        if (totalInteractions != null) {
            this.hasInteractions = totalInteractions > 0;
        }
    }
    
    // ADD THIS SETTER FOR int (primitive) type
    public void setTotalInteractions(int totalInteractions) {
        this.totalInteractions = totalInteractions;
        this.hasInteractions = totalInteractions > 0;
    }
    
    public void setCriticalInteractions(Integer criticalInteractions) { 
        this.criticalInteractions = criticalInteractions; 
    }
    
    // ADD THIS SETTER FOR int (primitive) type
    public void setCriticalInteractions(int criticalInteractions) {
        this.criticalInteractions = criticalInteractions;
    }
    
    public void setUserId(Long userId) { 
        this.userId = userId; 
    }
    
    public void setUserEmail(String userEmail) { 
        this.userEmail = userEmail; 
    }
    
    public void setSessionId(String sessionId) { 
        this.sessionId = sessionId; 
    }
    
    public void setCheckedAt(LocalDateTime checkedAt) { 
        this.checkedAt = checkedAt; 
    }
    
    public void setHasInteractions(Boolean hasInteractions) { 
        this.hasInteractions = hasInteractions; 
    }
    
    // ADD THIS SETTER FOR boolean (primitive) type
    public void setHasInteractions(boolean hasInteractions) {
        this.hasInteractions = hasInteractions;
    }
    
    public void setRecommendations(String recommendations) { 
        this.recommendations = recommendations; 
    }
    
    // Helper method to calculate severity summary
    public String getSeveritySummary() {
        if (criticalInteractions == null || totalInteractions == null) {
            return "No data";
        }
        if (totalInteractions == 0) {
            return "No interactions found";
        }
        if (criticalInteractions > 0) {
            return "Critical: " + criticalInteractions + " out of " + totalInteractions + " interactions";
        }
        return "Non-critical: " + totalInteractions + " interactions";
    }
    
    // Helper method to check if result contains critical interactions
    public boolean hasCriticalInteractions() {
        return criticalInteractions != null && criticalInteractions > 0;
    }
    
    @Override
    public String toString() {
        return "InteractionCheckResult{" +
                "id=" + id +
                ", medicationNames='" + medicationNames + '\'' +
                ", totalInteractions=" + totalInteractions +
                ", criticalInteractions=" + criticalInteractions +
                ", hasInteractions=" + hasInteractions +
                ", checkedAt=" + checkedAt +
                '}';
    }
}