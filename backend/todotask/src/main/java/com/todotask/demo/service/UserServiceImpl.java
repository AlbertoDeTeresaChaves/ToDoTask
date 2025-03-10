package com.todotask.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.todotask.demo.model.User;
import com.todotask.demo.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User updateUser(Long id, User userDetails) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		user.setUsername(userDetails.getUsername());
		
		if (!user.getEmail().equals(userDetails.getEmail())) {
			if (userRepository.findByEmail(userDetails.getEmail()).isPresent()) {
				throw new RuntimeException("Email ya en uso");
			}
			user.setEmail(userDetails.getEmail());
		}

		user.setPassword(userDetails.getPassword());

		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	@Transactional
	public boolean existsByEmail(String email) {

		return userRepository.findByEmail(email).isPresent();
	}

}
