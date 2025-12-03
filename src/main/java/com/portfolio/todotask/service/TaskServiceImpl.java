package com.portfolio.todotask.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.todotask.dto.TaskRequestDTO;
import com.portfolio.todotask.dto.TaskResponseDTO;
import com.portfolio.todotask.exception.ResourceNotFoundException;
import com.portfolio.todotask.mapper.TaskMapper;
import com.portfolio.todotask.mapper.UserMapper;
import com.portfolio.todotask.model.Task;
import com.portfolio.todotask.model.User;
import com.portfolio.todotask.repository.TaskRepository;
import com.portfolio.todotask.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final UserRepository userRepository;
	private final TaskMapper taskMapper;
	
	private User getUserById(Long userId) {
		
		return userRepository.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("Usuario no encontrado con el id: " + userId));
	}
	
	@Override
	@Transactional
	public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO, Long creatorId) {
		log.info("Intentando crear una tarea");
		
		User creator = getUserById(creatorId);
		
		Task task = taskMapper.toEntity(taskRequestDTO);
		
		task.setAssignedTo(creator);
		
		if(taskRequestDTO.getAssignedToId()!= null) {
			User userAssigned = getUserById(taskRequestDTO.getAssignedToId());
			task.setAssignedTo(userAssigned);
		}else {
			task.setAssignedTo(null);
		}
		
		Task savedTask = taskRepository.save(task);
		log.info("Tarea ID {} creada con exito:",savedTask.getId());
		return taskMapper.toDTO(savedTask);
	}

	@Override
	@Transactional(readOnly = true)
	public TaskResponseDTO findTaskById(Long id) {
		log.info("Intentando encontrar tarea con ID:{} ",id);
		return taskRepository.findById(id).map(taskMapper::toDTO).orElseThrow(
				()-> new ResourceNotFoundException("Tarea no encotrada con el id: " + id));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TaskResponseDTO> findAll(Pageable pageable) {
		log.info("Obteniendo lista paginada de tareas");
		return taskRepository.findAll(pageable).map(taskMapper::toDTO);
	}

	@Override
	@Transactional
	public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO) {
		log.info("Intentando actualizar la tarea con el id: ",id);
		
		Task task = taskRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Tarea no encontrado con id: " + id));
		
		Task updatedTask = taskMapper.toEntity(taskRequestDTO);
		
		task.setTitle(updatedTask.getTitle());
		task.setSubtitle(updatedTask.getSubtitle());
		task.setDescription(updatedTask.getDescription());
		task.setStatus(updatedTask.getStatus());
		task.setPriority(updatedTask.getPriority());
		task.setDueDate(updatedTask.getDueDate());
		
		
		if(taskRequestDTO.getAssignedToId()!=null) {
			User assignedTo = getUserById(taskRequestDTO.getAssignedToId());
			task.setAssignedTo(assignedTo);
		}else {
			task.setAssignedTo(null);
		}
		
		Task savedTask = taskRepository.save(task);
		log.info("Tarea ID {} actualizada con exito",savedTask.getId());
		
		return taskMapper.toDTO(savedTask);
	}

	@Override
	@Transactional
	public void deleteTask(Long id) {
		log.info("Intentando borrar la tarea ID {}",id);
		
		if(!taskRepository.existsById(id)){
			throw new ResourceNotFoundException("Tarea no encontrada para eliminar con id: " + id); 
		}
		
		taskRepository.deleteById(id);
		log.info("Tarea ID {} borrada con exito: ",id);

	}

}
