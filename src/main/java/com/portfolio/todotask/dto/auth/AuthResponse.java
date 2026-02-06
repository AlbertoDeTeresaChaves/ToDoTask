package com.portfolio.todotask.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.portfolio.todotask.dto.UserResponseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponse{
	
	@JsonProperty("user")
	private UserResponseDTO userResponseDTO;
	private String token;
	
}
