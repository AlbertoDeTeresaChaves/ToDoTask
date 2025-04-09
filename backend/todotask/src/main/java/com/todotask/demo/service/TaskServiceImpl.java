package com.todotask.demo.service;

import java.util.List;
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
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final UserRepository userRepository;

	public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
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
	public List<Task> findLatestTasks() {
		return taskRepository.findTop5ByOrderByUpdatedAtDesc();
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
		if (task.getAssigned() != null && task.getAssigned().getId() != null) {
			User existingUser = userRepository.findById(task.getAssigned().getId())
					.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
			task.setAssigned(existingUser);
		}
		return taskRepository.save(task);
	}

	@Override
	@Transactional
	public Task updateTask(Long id, Task taskDetails) {
		Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

		task.setTaskName(taskDetails.getTaskName());
		task.setDescription(taskDetails.getDescription());
		task.setStatus(taskDetails.getStatus());

	    if (taskDetails.getAssigned() != null && taskDetails.getAssigned().getId() != null) {
	        User existingUser = userRepository.findById(taskDetails.getAssigned().getId())
	                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	        System.out.println(existingUser.toString());
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
