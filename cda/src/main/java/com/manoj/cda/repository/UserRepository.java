package com.manoj.cda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manoj.cda.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>
{
	Optional<User> findByUsername(String username);
}
