package com.devsuperior.bds02.controllers.exceptions;

import java.io.Serializable;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	private Instant timestamp;
	private int statusCode;
	private String error;
	private String message;
	private String path;

	public StandardError(HttpStatus status, RuntimeException error, HttpServletRequest request) {
		this.timestamp = Instant.now();
		this.statusCode = status.value();
		this.error = status.getReasonPhrase();
		this.message = error.getMessage();
		this.path = request.getRequestURI();
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int status) {
		this.statusCode = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ResponseEntity<StandardError> toJsonResponse() {
		return ResponseEntity.status(statusCode).body(this);
	}

}
