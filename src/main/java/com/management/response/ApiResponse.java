package com.management.response;

public class ApiResponse {
	private String message;
	private String status;
	private Integer statusCode;
	private Object data;
	private long totalRecords;
	

	public ApiResponse() {
	}

	public ApiResponse(String message, String status, Integer statusCode, Object data, long totalRecords) {
		this.message = message;
		this.status = status;
		this.statusCode = statusCode;
		this.data = data;
		this.totalRecords = totalRecords;
	}

	
	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
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
