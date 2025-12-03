package com.portfolio.todotask.dto;

import java.time.LocalDate;

import com.portfolio.todotask.enums.TaskPriority;
import com.portfolio.todotask.enums.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 150, message = "El título no puede exceder los 150 caracteres")
    private String title;

    @Size(max = 255, message = "El subtítulo no puede exceder los 255 caracteres")
    private String subtitle;

    private String description;

    @NotNull(message = "El ID del creador (createdBy) es obligatorio")
    private Long createdById;

    private Long assignedToId; 

    private TaskStatus status;

    private TaskPriority priority;

    private LocalDate dueDate;
}