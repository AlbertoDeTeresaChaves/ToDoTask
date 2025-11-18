package com.portfolio.todotask.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

import com.portfolio.todotask.enums.Role;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(length = 50, nullable = false, unique = true)
    private String username; 

    @Column(length = 100, nullable = false, unique = true)
    private String email; 

    @Column(length = 255, nullable = false)
    private String password; 

    @Enumerated(EnumType.STRING) 
    @Column(columnDefinition = "ENUM('ADMIN', 'USER') DEFAULT 'USER'")
    private Role role = Role.USER; 

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean enabled = true;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;
}


