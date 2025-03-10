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

		try {
			return ResponseEntity.ok(userService.findAll());	
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		try {
			return ResponseEntity.ok(userService.findById(id));	
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User userDetails) {

	    try {
	        User updatedUser = userService.updateUser(id, userDetails);
	        return ResponseEntity.ok(updatedUser);
	    } catch (RuntimeException e) {
	        if (e.getMessage().equals("Usuario no encontrado")) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        } else if (e.getMessage().equals("Email ya en uso")) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	        }
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado");
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			userService.deleteById(id);
			return ResponseEntity.ok().build();	
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}
}
