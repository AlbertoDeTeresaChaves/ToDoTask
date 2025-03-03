package com.todotask.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<?> create (@RequestBody User user){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	
	@GetMapping
	public ResponseEntity<?> readAll (){
		
		return ResponseEntity.ok(userService.findAll());
	}
}
