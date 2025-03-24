package com.todotask.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todotask.demo.dto.AuthResponse;
import com.todotask.demo.dto.LoginRequest;
import com.todotask.demo.dto.RegisterRequest;

import com.todotask.demo.repository.UserRepository;

import com.todotask.demo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	private final AuthService authService;

	public AuthController(UserRepository userRepository, AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		try {
			AuthResponse response = authService.login(request);
			return ResponseEntity.ok(response);
		} catch (RuntimeException exception) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
		try {
			AuthResponse response = authService.register(request);
			return ResponseEntity.ok(response);
		} catch (RuntimeException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}

}
