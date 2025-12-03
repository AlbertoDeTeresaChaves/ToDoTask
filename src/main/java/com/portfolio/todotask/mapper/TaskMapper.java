package com.portfolio.todotask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.portfolio.todotask.dto.TaskRequestDTO;
import com.portfolio.todotask.dto.TaskResponseDTO;
import com.portfolio.todotask.model.Task;
import com.portfolio.todotask.model.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

	//Request to Entity
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "createdBy", source = "createdById") 
    @Mapping(target = "assignedTo", source = "assignedToId")
	Task toEntity(TaskRequestDTO dto);
	
	//Entity to Response
	@Mapping(target = "createdById", source = "createdBy.id")
    @Mapping(target = "assignedToId", source = "assignedTo.id")
    TaskResponseDTO toDTO(Task task);
	
	default User userFromId(Long id) {
		if(id == null) {
			return null;
		}
		
		User user = new User();
		user.setId(id);
		return user;
	}
}
