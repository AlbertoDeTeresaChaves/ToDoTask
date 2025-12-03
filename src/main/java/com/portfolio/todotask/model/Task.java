package com.portfolio.todotask.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.portfolio.todotask.enums.TaskPriority;
import com.portfolio.todotask.enums.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="tasks")
@NoArgsConstructor
@AllArgsConstructor
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title", length=150,nullable=false)
	private String title;
	
	@Column(name="subtitle",length=255)
	private String subtitle;
	
	@Lob
	@Column(name="description")
	private String description;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="assigned_to",referencedColumnName="id", foreignKey = @ForeignKey(name="FK_TASK_ASSIGNED"))
	private User assignedTo;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_TASK_CREATOR"))
    private User createdBy;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition="ENUM('PENDING', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED') DEFAULT 'PENDING'")
	private TaskStatus status = TaskStatus.PENDING;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition="ENUM('LOW', 'MEDIUM', 'HIGH') DEFAULT 'MEDIUM'")
	private TaskPriority priority = TaskPriority.MEDIUM;
	
	@Column(name="due_date")
	private LocalDate dueDate;
	
	@Column(name="created_at",updatable=false,nullable =false)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Column(name="updated_at",nullable =false)
	private LocalDateTime updatedAt = LocalDateTime.now();
	
	
}
