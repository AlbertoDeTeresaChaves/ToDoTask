package com.portfolio.todotask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.todotask.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{

}
