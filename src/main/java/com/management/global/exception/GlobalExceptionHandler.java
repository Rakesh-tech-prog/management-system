package com.management.global.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.management.exception.BusinessException;
import com.management.exception.TechnicalException;
import com.management.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * Handles BadCredentials exception and returns a standardized error response.
	 */
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiResponse> handleBadCredentials(BusinessException ex) {
		ApiResponse errorResponse = new ApiResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.toString(),
				HttpStatus.UNAUTHORIZED.value(),null,0);
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Handles BusinessException and returns a standardized error response.
	 */
	@ExceptionHandler(TechnicalException.class)
	public ResponseEntity<ApiResponse> handleBusinessException(BusinessException ex) {
		ApiResponse errorResponse = new ApiResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),null,0);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationErrors(
	            MethodArgumentNotValidException ex) {

	        Map<String, String> errors = new HashMap<>();

	        ex.getBindingResult().getFieldErrors().forEach(error ->
	                errors.put(error.getField(), error.getDefaultMessage())
	        );

	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
	 
}
