package com.todotask.demo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.todotask.demo.model.Task;

@Service
public interface TaskService {
	public Iterable<Task> findAll();
	
	public Page<Task> findAll(Pageable pageable);
	
	public Optional<Task> findById(Long id);
	
	public Optional<Task> findByTaskName(String taskName);
	
	public Task save(Task task);
	
	public Task updateTask(Long id, Task taskDetails);
	
	public void deleteById(Long id);
	
}
