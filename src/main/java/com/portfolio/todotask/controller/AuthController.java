package com.portfolio.todotask.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.todotask.dto.UserRequestDTO;
import com.portfolio.todotask.model.User;
import com.portfolio.todotask.repository.UserRepository;
import com.portfolio.todotask.security.JwtUtil;
import com.portfolio.todotask.service.UserService;

@RestController
@RequestMapping("api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	UserService userService;
	
	@PostMapping("/signin")
	public String authenticateUser(@RequestBody UserRequestDTO userRequestDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						userRequestDTO.getUsername(),
						userRequestDTO.getPassword()
						)
				);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return jwtUtil.generateToken(userDetails.getUsername());
	}
	
	@PostMapping("/signup")
	public String registerUser(@RequestBody UserRequestDTO userRequestDTO) {
		if(userRepository.existsByUsername(userRequestDTO.getUsername())) {
			return "Error: Username is already taken!";
		}
		
		userService.createUser(userRequestDTO);
		return "User registered";
		
		
	}
}
