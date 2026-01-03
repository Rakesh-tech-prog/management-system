package com.management.response;

import java.util.List;

import com.management.enm.QuestionType;

public class QuestionResponse {
	private Long id;
	private String questionText;
	private String assessmentId;
	private QuestionType questionType;
	private List<OptionResponse> options;

	public String getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public List<OptionResponse> getOptions() {
		return options;
	}

	public void setOptions(List<OptionResponse> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "QuestionResponse [id=" + id + ", questionText=" + questionText + ", assessmentId=" + assessmentId
				+ ", questionType=" + questionType + ", options=" + options + "]";
	}

}
