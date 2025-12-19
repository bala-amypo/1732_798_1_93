package com.example.demo.controller;

import com.example.demo.dto.InteractionCheckRequest;
import com.example.demo.model.InteractionCheckResult;
import com.example.demo.service.InteractionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interactions")
public class InteractionController {
    
    private final InteractionService interactionService;
    
    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }
    
    @PostMapping("/check")
    public InteractionCheckResult checkInteractions(@RequestBody InteractionCheckRequest request) {
        return interactionService.checkInteractions(request.getMedicationIds());
    }
    
    @GetMapping("/result/{id}")
    public InteractionCheckResult getResult(@PathVariable Long id) {
        return interactionService.getResult(id);
    }
}