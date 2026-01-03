package com.management.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Assessment")
public class Assessment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String assessmentCode;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAssessmentCode() {
		return assessmentCode;
	}

	public void setAssessmentCode(String assessmentCode) {
		this.assessmentCode = assessmentCode;
	}


	@Override
	public String toString() {
		return "Assessment [id=" + id + ", title=" + title + ", assessmentCode=" + assessmentCode + "]";
	}

	
	
}
