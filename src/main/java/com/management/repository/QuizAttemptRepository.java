package com.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entity.QuizAttempt;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
}
