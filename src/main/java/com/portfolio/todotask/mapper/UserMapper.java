package com.portfolio.todotask.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.portfolio.todotask.dto.UserRequestDTO;
import com.portfolio.todotask.dto.UserResponseDTO;
import com.portfolio.todotask.model.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	//Request to Entity
	@Mapping(target = "id",ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	User toEntity(UserRequestDTO dto);
	
	//Entity to Response
	UserResponseDTO toDTO(User user);
}
