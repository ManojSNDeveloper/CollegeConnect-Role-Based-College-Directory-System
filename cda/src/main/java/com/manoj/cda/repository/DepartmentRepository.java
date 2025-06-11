package com.manoj.cda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manoj.cda.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> 
{
	
}

