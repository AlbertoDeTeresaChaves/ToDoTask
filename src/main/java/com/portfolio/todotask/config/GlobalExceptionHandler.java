package com.portfolio.todotask.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.portfolio.todotask.exception.AuthenticationFailedException;
import com.portfolio.todotask.exception.ResourceConflictException;
import com.portfolio.todotask.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// STATUS 401
	@ExceptionHandler(AuthenticationFailedException.class)
	public ResponseEntity<Map<String,String>> handleAuthError(AuthenticationFailedException ex){
		Map<String,String> error = new HashMap<>();
		error.put("error", "Unathorized");
		error.put("message",ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
	// STATUS 404
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(ResourceNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	// STATUS 409
	@ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<String> handleConflictException(ResourceConflictException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); 
    }
	
}
