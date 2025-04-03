package com.todotask.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todotask.demo.dto.AuthResponse;
import com.todotask.demo.dto.LoginRequest;
import com.todotask.demo.dto.RegisterRequest;
import com.todotask.demo.mapper.UserMapper;
import com.todotask.demo.model.User;
import com.todotask.demo.model.UserDTO;
import com.todotask.demo.repository.UserRepository;
import com.todotask.demo.security.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final UserMapper userMapper;
	public AuthServiceImpl(UserRepository userRepository,UserMapper userMapper) {
	        this.userRepository = userRepository;
	        this.passwordEncoder = new BCryptPasswordEncoder();
	        this.userMapper = userMapper;

	    }

	@Override
	@Transactional
	public AuthResponse register(RegisterRequest request) {
		
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException ("Usuario ya registrado");
		}
		
		User user = new User();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		userRepository.save(user);
		
		String token = JwtUtil.generateToken(user.getEmail());
		UserDTO userDTO = userMapper.userToUserDTO(user);

        return new AuthResponse(token, userDTO);
	}
	
	@Override
	@Transactional
	public AuthResponse login(LoginRequest request) {
		Optional<User> user = userRepository.findByEmail(request.getEmail());
		
		if(user.isPresent() && passwordEncoder.matches(request.getPassword(),user.get().getPassword())) {
			String token = JwtUtil.generateToken(user.get().getEmail());
			UserDTO userDTO = userMapper.userToUserDTO(user.get());
    		return new AuthResponse(token,userDTO);
		}
		throw new RuntimeException("Credenciales incorrectas");
	}
	

}
