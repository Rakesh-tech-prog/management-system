package com.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entity.Assessment;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

}
