package com.management.model;

import java.util.ArrayList;
import java.util.List;


public class AssessmentRequest {

	private String title;
	private String assessmentCode;
	List<Long> questionIds = new ArrayList<>();

	public String getAssessmentCode() {
		return assessmentCode;
	}

	public void setAssessmentCode(String assessmentCode) {
		this.assessmentCode = assessmentCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Long> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(List<Long> questionIds) {
		this.questionIds = questionIds;
	}

	@Override
	public String toString() {
		return "AssessmentRequest [title=" + title + ", questionIds=" + questionIds + "]";
	}

}
