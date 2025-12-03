package com.portfolio.todotask.dto;

import com.portfolio.todotask.enums.TaskStatus;
import com.portfolio.todotask.enums.TaskPriority;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {

    private Long id;

    private String title;
    
    private String subtitle;

    private String description;

    private Long assignedToId;
    
    private Long createdById;

    private TaskStatus status;

    private TaskPriority priority;

    private LocalDate dueDate;

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}