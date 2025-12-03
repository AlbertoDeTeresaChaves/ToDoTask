package com.portfolio.todotask.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.portfolio.todotask.dto.TaskRequestDTO;
import com.portfolio.todotask.dto.TaskResponseDTO;

@Service
public interface TaskService {
	
	TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO,Long creatorId);
	TaskResponseDTO findTaskById(Long id);
	Page<TaskResponseDTO> findAll(Pageable pageable);
	TaskResponseDTO updateTask(Long id,TaskRequestDTO taskRequestDTO);
	void deleteTask(Long id);
}
