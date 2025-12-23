package com.example.demo.service;

import com.example.demo.model.InteractionCheckResult;
import java.util.List;

public interface InteractionService {
    
    InteractionCheckResult checkInteractions(List<Long> medicationIds);  // Changed from List<String>
    
    List<InteractionCheckResult> getAllResults();
    
    InteractionCheckResult getResult(Long id);  // Added this method
}