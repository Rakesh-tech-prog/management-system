package com.management.service;

import com.management.entity.Question;
import com.management.model.AssessmentRequest;
import com.management.model.QuestionRequest;
import com.management.model.QuizSubmitRequest;
import com.management.response.ApiResponse;

public interface QuestionService {

	public Question createQuestion(QuestionRequest req);
	public ApiResponse getAllQuestions(int pageNo, int pageRecords);
	public ApiResponse submitAssessment(AssessmentRequest request);
	public ApiResponse getAllQuestions();
	public ApiResponse updateQuestion(Long id, QuestionRequest req);
	public ApiResponse deleteQuestion(Long id);
	public ApiResponse submitQuiz(QuizSubmitRequest req);
}