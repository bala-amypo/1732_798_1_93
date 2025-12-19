package com.example.demo.service;

import com.example.demo.model.InteractionCheckResult;
import java.util.List;

public interface InteractionService {
    InteractionCheckResult checkInteractions(List<Long> medicationIds);
    InteractionCheckResult getResult(Long id);
    List<InteractionCheckResult> getAllResults();
}