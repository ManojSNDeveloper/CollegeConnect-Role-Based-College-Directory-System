package com.manoj.cda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manoj.cda.entity.FacultyProfile;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.entity.User;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Integer> 
{
	@Query("SELECT s FROM StudentProfile s WHERE s.year LIKE %:year%")
	List<StudentProfile> findByYear(@Param("year") String year);

	@Query("SELECT s FROM StudentProfile s WHERE s.name LIKE %:name%")
	List<StudentProfile> findByName(@Param("name") String string);

	@Query("SELECT s FROM StudentProfile s WHERE LOWER(s.department.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<StudentProfile> findByBranch(@Param("name") String name);
}

