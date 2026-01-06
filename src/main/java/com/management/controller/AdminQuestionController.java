package com.management.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.entity.Question;
import com.management.model.AssessmentRequest;
import com.management.model.QuestionRequest;
import com.management.response.ApiResponse;
import com.management.response.QuestionResponse;
import com.management.service.QuestionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminQuestionController {

	private final QuestionService adminQuestionService;
	Logger logger = LoggerFactory.getLogger(AdminQuestionController.class);

	public AdminQuestionController(QuestionService adminQuestionService) {
		this.adminQuestionService = adminQuestionService;
	}

	/**
	 *  Admin dashboard – list questions
	 */
	@GetMapping("/questions")
	public ResponseEntity<ApiResponse> getAll(@RequestParam int pageNo, @RequestParam int pageRecords) {
		logger.info("Admin requested all questions");
		return ResponseEntity.ok(adminQuestionService.getAllQuestions(pageNo, pageRecords));
	}
	
	/**
	 *  Admin creates question
	 */
	@PostMapping("/create")
	public ResponseEntity<QuestionResponse> create(@RequestBody @Valid QuestionRequest request) {
		logger.info("Admin creating a new question");
		Question q = adminQuestionService.createQuestion(request);

		QuestionResponse res = new QuestionResponse();
		res.setId(q.getId());
		res.setQuestionText(q.getQuestionText());
		res.setQuestionType(q.getQuestionType());

		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}

	/**
	 *  Admin updates question
	 */
	@PostMapping("/update/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody QuestionRequest request) {
		logger.info("Admin updating question with ID: {}", id);
		ApiResponse updateQuestion = adminQuestionService.updateQuestion(id, request);

		return ResponseEntity.status(HttpStatus.OK).body(updateQuestion);
	}

	/**
	 *  Admin deletes question
	 */

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
		logger.info("Admin deleting question with ID: {}", id);
		ApiResponse deleteQuestion = adminQuestionService.deleteQuestion(id);
		return ResponseEntity.status(HttpStatus.OK).body(deleteQuestion);
	}
  	/**
	 *  Admin submits assessment
	 */
	@PostMapping("/submit-assessment")
	public ResponseEntity<ApiResponse> submitAssessment(@RequestBody AssessmentRequest request) {
		logger.info("Admin submitting assessment for user: {}", request.getTitle());
		ApiResponse response = adminQuestionService.submitAssessment(request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
}
