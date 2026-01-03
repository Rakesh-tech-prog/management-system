package com.management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.management.enm.QuestionType;

@Entity
@Table(name = "questions")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String questionText;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private QuestionType questionType;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true,fetch = 
			FetchType.EAGER)
	private List<QuestionOption> options = new ArrayList<>();

	private String assessmentId;

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

	public List<QuestionOption> getOptions() {
		return options;
	}

	public void setOptions(List<QuestionOption> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", questionText=" + questionText + ", questionType=" + questionType + ", options="
				+ options + "]";
	}

}
