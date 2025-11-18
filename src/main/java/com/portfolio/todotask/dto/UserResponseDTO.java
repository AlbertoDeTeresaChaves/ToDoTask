package com.portfolio.todotask.dto;

import com.portfolio.todotask.enums.Role;

import lombok.Data;

@Data
public class UserResponseDTO {
	
	private Long id;
	private String username;
	private String email;
	private Role role;
	private boolean enabled;
}
