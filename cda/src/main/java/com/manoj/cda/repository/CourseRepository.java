package com.manoj.cda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manoj.cda.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> 
{
	@Query("SELECT s FROM Course s WHERE LOWER(s.department.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Course> findByBranch(@Param("name") String name);
}

