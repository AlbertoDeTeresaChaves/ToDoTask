package com.portfolio.todotask.controller;


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

import com.portfolio.todotask.dto.TaskRequestDTO;
import com.portfolio.todotask.dto.TaskResponseDTO;
import com.portfolio.todotask.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

	private final TaskService taskService;
	
	@PostMapping("/{creatorId}") // Cuando este el login se cambia con el SecurityContext
	public ResponseEntity<TaskResponseDTO> createTask(@PathVariable Long creatorId,@Valid @RequestBody TaskRequestDTO taskRequestDTO){
		TaskResponseDTO newTask = taskService.createTask(taskRequestDTO, creatorId);

		return ResponseEntity.status(201).body(newTask);
	}
	
	@GetMapping
	public ResponseEntity<Page<TaskResponseDTO>> getAllTasks(
			@PageableDefault(size = 20, page = 0, sort = "createdAt") Pageable pageable){
		
		return ResponseEntity.ok(taskService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id){
		
		return ResponseEntity.ok(taskService.findTaskById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id,@Valid @RequestBody TaskRequestDTO taskRequestDTO){
		
		return ResponseEntity.ok(taskService.updateTask(id, taskRequestDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id){
		taskService.deleteTask(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
