package com.management.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginRequest {
	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	private String username;

	@NotBlank(message = "Password is required")
	@Pattern(
			  regexp = "^(?=.*[A-Z])(?=.*[0-9]).*$",
			  message = "Password must contain at least one uppercase letter and one number"
			)
	@Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequest [username=" + username + ", password=" + password + "]";
	}

}
