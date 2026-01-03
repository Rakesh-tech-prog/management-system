package com.management.model;

public class QuizSubmitRequest {
	
	private String userId;
	private int score;
	private String assessmentId;
	private String status;



	public QuizSubmitRequest(String userId, int score, String assessmentId, String status) {
		super();
		this.userId = userId;
		this.score = score;
		this.assessmentId = assessmentId;
		this.status = status;
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

	@Override
	public String toString() {
		return "QuizSubmitRequest [userId=" + userId + ", score=" + score + ", assessmentId="
				+ assessmentId + ", status=" + status + "]";
	}

}
