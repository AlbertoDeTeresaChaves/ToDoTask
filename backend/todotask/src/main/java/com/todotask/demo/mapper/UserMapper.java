package com.todotask.demo.mapper;

import org.springframework.stereotype.Component;

import com.todotask.demo.model.User;
import com.todotask.demo.model.UserDTO;

@Component
public class UserMapper {
	
	  public UserDTO userToUserDTO(User user) {
	        UserDTO dto = new UserDTO();
	        dto.setId(user.getId());
	        dto.setUsername(user.getUsername());
	        dto.setEmail(user.getEmail());
	        return dto;
	    }
}
