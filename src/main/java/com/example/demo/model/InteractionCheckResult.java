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
    
    // Optional: Reference to medications if needed
    /*
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "result_medications",
        joinColumns = @JoinColumn(name = "result_id"),
        inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private Set<Medication> medications = new HashSet<>();
    */
    
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
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getMedicationNames() { return medicationNames; }
    public void setMedicationNames(String medicationNames) { this.medicationNames = medicationNames; }
    
    public String getMedicationIds() { return medicationIds; }
    public void setMedicationIds(String medicationIds) { this.medicationIds = medicationIds; }
    
    public String getInteractions() { return interactions; }
    public void setInteractions(String interactions) { 
        this.interactions = interactions;
        if (interactions != null) {
            this.hasInteractions = !interactions.isEmpty();
        }
    }
    
    public Integer getTotalInteractions() { return totalInteractions; }
    public void setTotalInteractions(Integer totalInteractions) { 
        this.totalInteractions = totalInteractions;
        this.hasInteractions = totalInteractions != null && totalInteractions > 0;
    }
    
    public Integer getCriticalInteractions() { return criticalInteractions; }
    public void setCriticalInteractions(Integer criticalInteractions) { this.criticalInteractions = criticalInteractions; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    
    public LocalDateTime getCheckedAt() { return checkedAt; }
    public void setCheckedAt(LocalDateTime checkedAt) { this.checkedAt = checkedAt; }
    
    public Boolean getHasInteractions() { return hasInteractions; }
    public void setHasInteractions(Boolean hasInteractions) { this.hasInteractions = hasInteractions; }
    
    public String getRecommendations() { return recommendations; }
    public void setRecommendations(String recommendations) { this.recommendations = recommendations; }
    
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