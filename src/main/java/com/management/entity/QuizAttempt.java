package com.management.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quiz_attempt")
public class QuizAttempt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userId;
	private int score;
	private String assessmentId;
	private String status;
	private LocalDateTime submittedAt;
	
	
	

	public QuizAttempt(String userId, int score, String assessmentId, String status, LocalDateTime submittedAt) {
		super();
		this.userId = userId;
		this.score = score;
		this.assessmentId = assessmentId;
		this.status = status;
		this.submittedAt = submittedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getSubmittedAt() {
		return submittedAt;
	}

	public void setSubmittedAt(LocalDateTime submittedAt) {
		this.submittedAt = submittedAt;
	}

	@Override
	public String toString() {
		return "QuizAttempt [id=" + id + ", userId=" + userId + ", score=" + score + ", assessmentId=" + assessmentId
				+ ", status=" + status + ", submittedAt=" + submittedAt + "]";
	}

}
