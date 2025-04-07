package com.todotask.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todotask.demo.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
	Optional<Task>findByTaskName(String taskName);
	List<Task> findTop5ByOrderByUpdatedAtDesc();
}
