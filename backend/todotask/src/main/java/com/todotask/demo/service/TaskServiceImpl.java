package com.todotask.demo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todotask.demo.model.Task;
import com.todotask.demo.model.User;
import com.todotask.demo.repository.TaskRepository;
import com.todotask.demo.repository.UserRepository;

@Service
public class TaskServiceImpl implements TaskService{

	private final TaskRepository taskRepository;
	private final UserRepository userRepository;
	
	public TaskServiceImpl(TaskRepository taskRepository,UserRepository userRepository) {
		this.taskRepository = taskRepository;
        this.userRepository = userRepository;

	}

	@Override
    @Transactional(readOnly = true) 
	public Iterable<Task> findAll() {
		
		return taskRepository.findAll();
	}

	@Override
    @Transactional(readOnly = true)
	public Page<Task> findAll(Pageable pageable) {
		
		return taskRepository.findAll(pageable);
	}

	@Override
    @Transactional(readOnly = true)
	public Optional<Task> findById(Long id) {
		
		return taskRepository.findById(id);
	}

	@Override
    @Transactional(readOnly = true)
	public Optional<Task> findByTaskName(String taskName) {
		
		return taskRepository.findByTaskName(taskName);
	}

	@Override
	@Transactional
	public Task save(Task task) {
		 if (task.getAssigned() != null && task.getAssigned().getUserId() != null) {
	            User existingUser = userRepository.findById(task.getAssigned().getUserId())
	                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	            task.setAssigned(existingUser);
	        }
		return taskRepository.save(task);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		taskRepository.deleteById(id);
		
	}

}
