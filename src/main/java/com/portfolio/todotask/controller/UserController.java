package com.portfolio.todotask.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.portfolio.todotask.dto.UserRequestDTO;
import com.portfolio.todotask.dto.UserResponseDTO;
import com.portfolio.todotask.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
		UserResponseDTO newUser = userService.createUser(userRequestDTO);
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
		
		return ResponseEntity.created(location).body(newUser);
	}
	
	@GetMapping
	public ResponseEntity<Page<UserResponseDTO>> getAllUsers(
			@PageableDefault(size = 10, page = 0, sort = "username")Pageable pageable){
				
				return ResponseEntity.ok(userService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
		return ResponseEntity.ok(userService.findById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id , @Valid @RequestBody UserRequestDTO userRequestDTO){
		return ResponseEntity.ok(userService.updateUser(id, userRequestDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
}
