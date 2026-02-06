package com.portfolio.todotask.service;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.portfolio.todotask.dto.UserRequestDTO;
import com.portfolio.todotask.dto.UserResponseDTO;
import com.portfolio.todotask.dto.auth.AuthRequest;
import com.portfolio.todotask.dto.auth.AuthResponse;
import com.portfolio.todotask.exception.AuthenticationFailedException;

import com.portfolio.todotask.mapper.UserMapper;
import com.portfolio.todotask.model.User;
import com.portfolio.todotask.repository.UserRepository;
import com.portfolio.todotask.security.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final UserService userService;

	
	public AuthResponse authenticate(AuthRequest request) {
		
		String password = request.getPassword();
		
		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> {
				log.error("Login failed: User {} not exist",request.getUsername());
				return new AuthenticationFailedException("Invalid username or password");
				}); 
		
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			log.error("Login failed: Incorrect password for user {}:",request.getUsername());
			throw new AuthenticationFailedException("Invalid username or password");
		}

		String token = jwtUtil.generateToken(request.getUsername());
		
		return AuthResponse.builder()
				.userResponseDTO(userMapper.toDTO(user))
				.token(token)
				.build();
	}
	
	public AuthResponse register(UserRequestDTO request) {	
		
		UserResponseDTO userResponse = userService.createUser(request);
		
		String token = jwtUtil.generateToken(request.getUsername());
		
		return AuthResponse.builder()
				.userResponseDTO(userResponse)
				.token(token)
				.build();
	}
}
