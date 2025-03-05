package com.todotask.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todotask.demo.model.User;
import com.todotask.demo.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user) {

		if(userService.existsByEmail(user.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo ya está en uso");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}

	@GetMapping
	public ResponseEntity<?> readAll() {

		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<User> OptionalUser = userService.findById(id);

		if (!OptionalUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(OptionalUser);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User user) {
		Optional<User> OptionalUser = userService.findById(id);

		if (!OptionalUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		OptionalUser.get().setUsername(user.getUsername());
		OptionalUser.get().setEmail(user.getEmail());
		OptionalUser.get().setPassword(user.getPassword());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(OptionalUser.get()));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Optional<User> OptionalUser = userService.findById(id);

		if (!OptionalUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		userService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
