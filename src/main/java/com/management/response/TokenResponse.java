package com.management.response;

public class TokenResponse {

	private String message;
	private String status;
	private Integer statusCode;
	private Object token;
	private String role;

	public TokenResponse() {
	}


	
	public TokenResponse(String message, String status, Integer statusCode, Object token, String role) {
		super();
		this.message = message;
		this.status = status;
		this.statusCode = statusCode;
		this.token = token;
		this.role = role;
	}



	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public Object getToken() {
		return token;
	}

	public void setToken(Object token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public String getStatus() {
		return status;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "SuccessResponse [message=" + message + ", status=" + status + ", statusCode=" + statusCode + "]";
	}

}
