package com.management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.management.exception.BusinessException;
import com.management.model.LoginRequest;
import com.management.response.TokenResponse;
import com.management.utility.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;

	Logger logger = LoggerFactory.getLogger(AuthController.class);

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	/**
	 *  User login – generate JWT token
	 */

	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
		TokenResponse response = new TokenResponse();
		logger.info("Login attempt for user: {}", loginRequest.getUsername());
		try {

			logger.info("Authenticating user: {}", loginRequest.getUsername());
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			String role = authenticate.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority)
					.orElse(null);

			String token = jwtUtil.generateToken(loginRequest.getUsername());
			logger.info("User authenticated successfully: {}", loginRequest.getUsername());
			response = new TokenResponse("Login Successful", "Success", 200, token, role);

		} catch (Exception e) {
			logger.error("Invalid Credentials: {}", e.getMessage());
			throw new BusinessException("Invalid Credentials");
		}

		return ResponseEntity.status(200).body(response);
	}
}
