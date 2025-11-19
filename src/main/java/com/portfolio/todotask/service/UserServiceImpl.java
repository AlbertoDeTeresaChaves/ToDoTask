package com.portfolio.todotask.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.todotask.dto.UserRequestDTO;
import com.portfolio.todotask.dto.UserResponseDTO;
import com.portfolio.todotask.enums.Role;
import com.portfolio.todotask.mapper.UserMapper;
import com.portfolio.todotask.model.User;
import com.portfolio.todotask.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	

	@Override
	@Transactional
	public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
		log.info("Intentando crear usuario con email: {}", userRequestDTO.getEmail());
		
		if(userRepository.existsByEmail(userRequestDTO.getEmail())) {
			throw new RuntimeException("Email ya registrado");
		}
		if(userRepository.existsByUsername(userRequestDTO.getUsername())) {
			throw new RuntimeException("Nombre de usuario ya registrado");
		}
		
		User user = userMapper.toEntity(userRequestDTO);
		
		user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
		user.setRole(Role.USER);
		
		User savedUser = userRepository.save(user);
		log.info("Usuario creado con éxito: ID {}", savedUser.getId());
		return userMapper.toDTO(savedUser);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<UserResponseDTO> findAll(Pageable pageable) {
		log.info("Obteniendo lista paginada de usuarios");
		
		return userRepository.findAll(pageable).map(userMapper::toDTO);
	}

	@Override
	@Transactional(readOnly=true)
	public UserResponseDTO findById(Long id) {
		log.debug("Buscando usuario ID: {}", id);
		
		return userRepository.findById(id).map(userMapper::toDTO)
				.orElseThrow(()-> new RuntimeException("Usuario no encotrado con el id: " + id));
	}

	@Override
	@Transactional
	public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
		log.info("Intentado actualizar el usuario con id:{}",id);
		
		User user = userRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Usuario no encontrado con el id"));
		
		if(!user.getUsername().equals(userRequestDTO.getUsername())) {
			if(userRepository.existsByUsername(userRequestDTO.getUsername())){
				throw new RuntimeException("Nombre de usuario ya en uso");
			}
			user.setUsername(userRequestDTO.getUsername());
		}
		
		if(!user.getEmail().equals(userRequestDTO.getEmail())) {
			if(userRepository.existsByEmail(userRequestDTO.getEmail())){
				throw new RuntimeException("Email ya en uso");
			}
			user.setEmail(userRequestDTO.getEmail());
		}
		
		if(userRequestDTO.getPassword() != null && !userRequestDTO.getPassword().isBlank()) {
			
			user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
		}
		
		User savedUser = userRepository.save(user);
		
		log.info("Usuario actualizado con éxito: ID {}", savedUser.getId());
		return userMapper.toDTO(savedUser);
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {
		log.info("Intentado borrar el usuario con id:{}",id);
		
		if(!userRepository.existsById(id)) {
			throw new RuntimeException("Usuario no encotrado para eliminar");
		}
	
		userRepository.deleteById(id);
		log.info("Usuario borrado con exito");
	}


}
