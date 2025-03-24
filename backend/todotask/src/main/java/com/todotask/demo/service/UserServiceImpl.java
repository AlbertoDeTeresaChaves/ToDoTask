package com.todotask.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.todotask.demo.model.User;
import com.todotask.demo.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {

		Iterable<User> usersList = userRepository.findAll();

		if (!usersList.iterator().hasNext()) {
			throw new RuntimeException("No hay usuarios registrados");
		}

		return usersList;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findAll(Pageable pageable) {

		Page<User> usersList = userRepository.findAll(pageable);

		if (usersList.isEmpty()) {
			throw new RuntimeException("No hay usuarios registrados");
		}

		return usersList;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		return Optional.of(userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID " + id)));
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

		user.setPassword(passwordEncoder.encode(userDetails.getPassword()));

		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty()) {
			throw new RuntimeException("Usuario no encotrado con ID: "+id);
		}
		
		userRepository.deleteById(id);
	}

	@Override
	@Transactional
	public boolean existsByEmail(String email) {

		return userRepository.findByEmail(email).isPresent();
	}

}
