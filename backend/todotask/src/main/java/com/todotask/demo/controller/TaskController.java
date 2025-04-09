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

import com.todotask.demo.model.Task;
import com.todotask.demo.service.TaskService;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

	@Autowired
	public TaskService taskService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Task task) {

		return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(task));
	}

	@GetMapping
	public ResponseEntity<?> readAll() {
		return ResponseEntity.ok(taskService.findAll());
	}
	
	@GetMapping("/latest")
	public ResponseEntity<?> getLatestTasks(){
		return ResponseEntity.ok(taskService.findLatestTasks());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long taskId) {

		return taskService.findById(taskId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Task> update(@PathVariable(value = "id") Long taskId, @RequestBody Task taskDetails) {

		return ResponseEntity.ok(taskService.updateTask(taskId, taskDetails));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long taskId) {

		Optional<Task> OptionalTask = taskService.findById(taskId);

		if (!OptionalTask.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		taskService.deleteById(taskId);
		return ResponseEntity.ok().build();
	}
}
