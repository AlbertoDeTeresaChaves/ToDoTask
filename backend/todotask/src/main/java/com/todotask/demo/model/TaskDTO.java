package com.todotask.demo.model;

import java.time.LocalDateTime;

import com.todotask.demo.model.Task.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskDTO {
    private Long taskId;
    private String taskName;
    private TaskStatus status;
    private Long assignedUserId; 
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
