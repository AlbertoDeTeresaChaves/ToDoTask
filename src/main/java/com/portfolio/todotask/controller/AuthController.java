package com.portfolio.todotask.controller;

import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.todotask.dto.UserRequestDTO;
import com.portfolio.todotask.dto.auth.AuthRequest;
import com.portfolio.todotask.dto.auth.AuthResponse;
import com.portfolio.todotask.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/auth")
public class AuthController {
	
	@Value("${cookie.name}")
	private String cookieName;
	@Value("${cookie.expiration}")
	private int cookieExpiration;
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest request,HttpServletResponse response) {

		AuthResponse auth = authService.authenticate(request);
		
		setCookie(auth.getToken(),response);

		return ResponseEntity.ok(auth);
	}
	
	public void setCookie(String token, HttpServletResponse response) {
		ResponseCookie cookie = ResponseCookie.from(cookieName, token)
				.httpOnly(true)
				.secure(true)
				.path("/")
                .maxAge(cookieExpiration)
                .sameSite(SameSiteCookies.STRICT.toString())
                .build();
		
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
	}
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> registerUser(@RequestBody UserRequestDTO userRequestDTO,HttpServletResponse response) {
		
		AuthResponse auth = authService.register(userRequestDTO);
		
		setCookie(auth.getToken(),response);
		
		return ResponseEntity.ok(auth);
		
		
	}
	
}
