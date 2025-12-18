package com.example.demo.repository;

import com.example.demo.model.InteractionCheckResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractionCheckResultRepository extends JpaRepository<InteractionCheckResult, Long> {
}