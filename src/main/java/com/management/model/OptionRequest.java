package com.management.model;

import jakarta.validation.constraints.NotBlank;

public class OptionRequest {
	@NotBlank
    private String optionText;

    private boolean correct;

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	@Override
	public String toString() {
		return "OptionRequest [optionText=" + optionText + ", correct=" + correct + "]";
	}
    
    
}
