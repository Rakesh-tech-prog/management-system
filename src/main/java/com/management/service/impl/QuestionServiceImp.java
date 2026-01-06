package com.management.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.enm.QuestionType;
import com.management.entity.Assessment;
import com.management.entity.Question;
import com.management.entity.QuestionOption;
import com.management.entity.QuizAttempt;
import com.management.exception.BusinessException;
import com.management.exception.TechnicalException;
import com.management.model.AssessmentRequest;
import com.management.model.OptionRequest;
import com.management.model.QuestionRequest;
import com.management.model.QuizSubmitRequest;
import com.management.repository.AssessmentRepository;
import com.management.repository.QuestionRepository;
import com.management.repository.QuizAttemptRepository;
import com.management.response.ApiResponse;
import com.management.response.OptionResponse;
import com.management.response.QuestionResponse;
import com.management.service.QuestionService;

@Service
@Transactional
public class QuestionServiceImp implements QuestionService {

	private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImp.class);

	private final QuestionRepository questionRepository;
	private final QuizAttemptRepository quizAttemptRepository;
	private final AssessmentRepository assessmentRepository;

	public QuestionServiceImp(QuestionRepository questionRepository, QuizAttemptRepository quizAttemptRepository,
			AssessmentRepository assessmentRepository) {
		this.questionRepository = questionRepository;
		this.quizAttemptRepository = quizAttemptRepository;
		this.assessmentRepository = assessmentRepository;
	}

	/*
	 * Admin creates question
	 */
	@Override
	@Transactional
	public ApiResponse createQuestion(QuestionRequest request) {
		logger.info("Creating question: {}", request.getQuestionText());
		try {
			Question question;
				QuestionResponse res = new QuestionResponse();
			validateQuestionRequest(request);
			Optional<Question> existingQuestion = questionRepository
					.findByQuestionTextIgnoreCase(request.getQuestionText().trim());
			if (existingQuestion.isPresent()) {
				question = existingQuestion.get();
				logger.info("Duplicate found! Updating existing question ID: {}", question.getId());
				question.getOptions().clear();
			} else {
				question = new Question();
			}
			question.setQuestionText(request.getQuestionText());
			question.setQuestionType(request.getQuestionType());

			mapOptionsToEntity(request.getOptions(), question);
            res.setId(question.getId());
			res.setQuestionText(question.getQuestionText());
			res.setQuestionType(question.getQuestionType());
			 questionRepository.save(question);
				return new ApiResponse("Question created successfully", "Success", 200, null, 0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error creating question: {}", e.getMessage());
			throw new TechnicalException("Failed to create question: " + e.getMessage());
		}
	}

	/*
	 * User dashboard – list questions
	 */
	@Override
	public ApiResponse getAllQuestions() {
		try {
			logger.info("Fetching all questions");
			List<Question> questions = questionRepository.findAll();
			List<QuestionResponse> response = questions.stream().map(this::mapToResponse).collect(Collectors.toList());

			return new ApiResponse("Questions retrieved successfully", "Success", 200, response, response.size());
		} catch (Exception e) {
			logger.error("Error while fetching all questions: {}", e.getMessage());
			throw new TechnicalException("Failed to retrieve questions");
		}
	}

	/*
	 * Admin dashboard – paginated list of questions
	 */

	@Override
	public ApiResponse getAllQuestions(int pageNo, int pageRecords) {
		try {
			logger.info("Fetching paginated questions: pageNo={}, pageRecords={}", pageNo, pageRecords);
			Page<Question> pageResult = questionRepository.findAll(PageRequest.of(pageNo, pageRecords));

			List<QuestionResponse> response = pageResult.getContent().stream().map(this::mapToResponse)
					.collect(Collectors.toList());

			return new ApiResponse("Page retrieved successfully", "Success", 200, response,
					pageResult.getTotalElements());
		} catch (Exception e) {
			logger.error("Error fetching paginated questions: {}", e.getMessage());
			throw new TechnicalException("Failed to retrieve paginated questions");
		}
	}

	/*
	 * * Admin updates question
	 */
	@Override
	@Transactional
	public ApiResponse updateQuestion(Long id, QuestionRequest req) {
		try {
			logger.info("Updating question ID: {}", id);
			validateQuestionRequest(req);

			Question question = questionRepository.findById(id)
					.orElseThrow(() -> new BusinessException("Question not found with ID: " + id));

			question.setQuestionText(req.getQuestionText());
			question.setQuestionType(req.getQuestionType());

			question.getOptions().clear();
			mapOptionsToEntity(req.getOptions(), question);

			questionRepository.save(question);
			logger.info("Question ID: {} updated successfully", id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error updating question ID {}: {}", id, e.getMessage());
			throw new TechnicalException("Failed to update question: " + e.getMessage());
		}
		return new ApiResponse("Question updated successfully", "Success", 200, null, 0);
	}

	/*
	 * Admin deletes question
	 */
	@Override
	@Transactional
	public ApiResponse deleteQuestion(Long id) {
		logger.info("Deleting question ID: {}", id);
		try {
			if (!questionRepository.existsById(id)) {
				throw new BusinessException("Question not found");
			}
			questionRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error deleting question ID {}: {}", id, e.getMessage());
			throw new TechnicalException("Failed to delete question: " + e.getMessage());
		}
		logger.info("Question ID: {} deleted successfully", id);
		return new ApiResponse("Question deleted successfully", "Success", 200, null, 0);
	}

	/*
	 * Admin submits assessment
	 */

	@Override
	@Transactional
	public ApiResponse submitAssessment(AssessmentRequest request) {
		try {
			logger.info("Submitting assessment: {}", request.getTitle());
			Assessment assessment = new Assessment();
			assessment.setTitle(request.getTitle());
			assessment.setAssessmentCode(request.getAssessmentCode());
			assessmentRepository.save(assessment);
			logger.info("Assessment created with code: {}", request.getAssessmentCode());
			questionRepository.assignAssessmentIdToQuestions(request.getAssessmentCode(), request.getQuestionIds());

			return new ApiResponse("Assessment created and questions linked", "Success", 200, null, 0);
		} catch (Exception e) {
			logger.error("Assessment submission failed: {}", e.getMessage());
			throw new TechnicalException("Error: " + e.getMessage());
		}
	}

	@Override
	@Transactional
	public ApiResponse submitQuiz(QuizSubmitRequest req) {
		QuizAttempt attempt = new QuizAttempt(req.getUserId(), req.getScore(), req.getAssessmentId(), req.getStatus(),
				LocalDateTime.now());
		quizAttemptRepository.save(attempt);
		return new ApiResponse("Quiz result saved", "Success", 200, null, 0);
	}

	/*
	 * * Helper methods
	 */

	private void validateQuestionRequest(QuestionRequest request) {
		if (request.getQuestionType() == QuestionType.TEXT)
			return;

		if (request.getOptions() == null || request.getOptions().size() < 2) {
			throw new BusinessException("Minimum 2 options required");
		}

		long correctCount = request.getOptions().stream().filter(OptionRequest::isCorrect).count();

		if (request.getQuestionType() == QuestionType.TRUE_FALSE && correctCount != 1) {
			throw new BusinessException("True/False must have exactly one correct option");
		}

		if (request.getQuestionType() == QuestionType.MCQ && correctCount < 1) {
			throw new BusinessException("MCQ must have at least one correct option");
		}
	}

	/* Mapping methods */

	private void mapOptionsToEntity(List<OptionRequest> optionRequests, Question question) {
		if (optionRequests == null)
			return;

		optionRequests.forEach(o -> {
			QuestionOption option = new QuestionOption();
			option.setOptionText(o.getOptionText());
			option.setCorrect(o.isCorrect());
			option.setQuestion(question);
			question.getOptions().add(option);
		});
	}

	private QuestionResponse mapToResponse(Question q) {
		QuestionResponse res = new QuestionResponse();
		res.setId(q.getId());
		res.setQuestionText(q.getQuestionText());
		res.setQuestionType(q.getQuestionType());
		res.setAssessmentId(q.getAssessmentId());

		if (q.getOptions() != null) {
			List<OptionResponse> options = q.getOptions().stream().map(opt -> {
				OptionResponse o = new OptionResponse();
				o.setId(opt.getId());
				o.setOptionText(opt.getOptionText());
				o.setCorrect(opt.isCorrect()); // Consistency fix: using isCorrect()
				return o;
			}).collect(Collectors.toList());
			res.setOptions(options);
		}
		return res;
	}
}
