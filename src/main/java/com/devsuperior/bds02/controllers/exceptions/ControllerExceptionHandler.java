package com.devsuperior.bds02.controllers.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> notFound(ResourceNotFoundException e, HttpServletRequest request) {
		final HttpStatus status = HttpStatus.NOT_FOUND;
		final StandardError httpError = new StandardError(status, e, request);

		return httpError.toJsonResponse();
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> databaseIntegrity(DatabaseException e, HttpServletRequest request) {
		final HttpStatus status = HttpStatus.BAD_REQUEST;
		final StandardError httpError = new StandardError(status, e, request);

		return httpError.toJsonResponse();
	}

}
