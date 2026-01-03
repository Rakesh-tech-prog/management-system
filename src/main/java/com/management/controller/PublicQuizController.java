package com.management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.model.QuizSubmitRequest;
import com.management.response.ApiResponse;
import com.management.service.QuestionService;

@RestController
@RequestMapping("/public")
public class PublicQuizController {

	private final QuestionService adminQuestionService;

	Logger logger = LoggerFactory.getLogger(PublicQuizController.class);

	public PublicQuizController(QuestionService adminQuestionService) {
		this.adminQuestionService = adminQuestionService;
	}

	/**
	 * User quiz – list questions
	 */
	@GetMapping("/questions")
	public ApiResponse getQuiz() {
		logger.info("Fetching all questions for public quiz");
		return adminQuestionService.getAllQuestions();
	}

	/**
	 * User quiz – submit answers
	 */

	@PostMapping("/submit")
	public ApiResponse submitQuiz(@RequestBody QuizSubmitRequest request) {
		logger.info("Submitting quiz for user: {}", request.getUserId());
		return adminQuestionService.submitQuiz(request);
	}
}
