package com.management.model;

import java.util.List;

import com.management.enm.QuestionType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class QuestionRequest {
	    @NotBlank
	    private String questionText;

	    @NotNull
	    private QuestionType questionType;
         
	    @NotEmpty(message = "Options are required")
	    @Size(min = 1, max = 6, message = "Options must be between 2 and 6")
	    @Valid 
	    private List<OptionRequest> options;

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

		public List<OptionRequest> getOptions() {
			return options;
		}

		public void setOptions(List<OptionRequest> options) {
			this.options = options;
		}

		@Override
		public String toString() {
			return "QuestionRequest [questionText=" + questionText + ", questionType=" + questionType + ", options="
					+ options + "]";
		}


}
