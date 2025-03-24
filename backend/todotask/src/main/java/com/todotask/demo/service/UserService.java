package com.todotask.demo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.todotask.demo.model.User;

@Service
public interface UserService {

	public Iterable<User> findAll();
	
	public Page<User> findAll(Pageable pageable);
	
	public Optional<User> findById(Long id);
	
	public User updateUser(Long id, User userDetails);
	
	public void deleteById(Long id);
	
	public boolean existsByEmail(String email);
}
