package com.manoj.cda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manoj.cda.entity.AcademicSection;
import com.manoj.cda.entity.StudentProfile;

public interface AcademicSectionRepository extends JpaRepository<AcademicSection, Integer> 
{
//	@Query("SELECT a FROM AcademicSection a WHERE LOWER(a.stdProfile.department.name) = LOWER(:branch)")
//	List<AcademicSection> findByBranch(@Param("branch") String branch);
	
	@Query("SELECT a FROM AcademicSection a WHERE LOWER(a.stdProfile.department.name) LIKE LOWER(CONCAT('%', :branch, '%'))")
	List<AcademicSection> findByBranch(@Param("branch") String branch);


}
