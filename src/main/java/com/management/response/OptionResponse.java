package com.management.response;

public class OptionResponse {
	private Long id;
	private String optionText;
	private boolean correct;
	
	public boolean getCorrect() {
		return correct;
	}
	
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOptionText() {
		return optionText;
	}
	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}
	
	
}
