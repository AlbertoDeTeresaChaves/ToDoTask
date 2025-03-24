package com.todotask.demo.service;

import org.springframework.stereotype.Service;

import com.todotask.demo.dto.AuthResponse;
import com.todotask.demo.dto.LoginRequest;
import com.todotask.demo.dto.RegisterRequest;

@Service
public interface AuthService {

	public AuthResponse register(RegisterRequest request);
	public AuthResponse login (LoginRequest request);
}
