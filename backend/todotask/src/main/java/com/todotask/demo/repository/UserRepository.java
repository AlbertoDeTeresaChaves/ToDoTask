package com.todotask.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todotask.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);
}
