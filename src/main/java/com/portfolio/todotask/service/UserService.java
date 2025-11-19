package com.portfolio.todotask.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.portfolio.todotask.dto.UserRequestDTO;
import com.portfolio.todotask.dto.UserResponseDTO;

@Service
public interface UserService {
	
	UserResponseDTO createUser(UserRequestDTO userRequestDTO);
	Page<UserResponseDTO> findAll(Pageable pageable);
	UserResponseDTO findById(Long id);
	UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
	void deleteUser(Long id);

	
}
