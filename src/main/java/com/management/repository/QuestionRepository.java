package com.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.management.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	
	@Modifying
	@Transactional
	@Query("UPDATE Question q SET q.assessmentId = :assessmentId WHERE q.id IN :ids")
	void assignAssessmentIdToQuestions(
	        @Param("assessmentId") String assessmentId,
	        @Param("ids") List<Long> ids
	);

}

