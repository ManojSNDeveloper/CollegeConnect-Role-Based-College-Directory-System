package com.manoj.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.manoj.cda.entity.AcademicSection;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.repository.AcademicSectionRepository;

@Repository
public class AcademicSectionDao 
{
	@Autowired
	private AcademicSectionRepository repo;

	public AcademicSection saveAcademic(AcademicSection section) 
	{
		return repo.save(section);
	}

	public List<AcademicSection> viewAll() 
	{
		return repo.findAll();
	}

	public Optional<AcademicSection> viewById(int id) 
	{
		return repo.findById(id);
	}
	

	public List<AcademicSection> findByBranch(String branch) 
	{
		return repo.findByBranch(branch);  
	}

	public Optional<AcademicSection> findById(int stdId) {
	    return repo.findById(stdId);
	}

	public AcademicSection updateAcademic(AcademicSection section) {
	    return repo.save(section);
	}

 
	 
}
