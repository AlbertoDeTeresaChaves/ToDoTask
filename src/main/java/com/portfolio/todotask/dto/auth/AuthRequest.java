package com.portfolio.todotask.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

	private String username;
	private String password;
	
	public AuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
}
