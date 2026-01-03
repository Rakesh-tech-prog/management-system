package com.management.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.management.enm.QuestionType;

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
