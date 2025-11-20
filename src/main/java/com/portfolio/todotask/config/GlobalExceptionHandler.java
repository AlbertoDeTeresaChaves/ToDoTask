package com.portfolio.todotask.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.portfolio.todotask.exception.ResourceConflictException;
import com.portfolio.todotask.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// STATUS 409
	@ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<String> handleConflictException(ResourceConflictException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); 
    }
	
	// STATUS 404
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(ResourceNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
}
