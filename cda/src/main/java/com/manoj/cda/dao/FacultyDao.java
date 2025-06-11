package com.manoj.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.manoj.cda.entity.FacultyProfile;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.repository.FacultyProfileRepository;

@Repository
public class FacultyDao 
{
	@Autowired
	private FacultyProfileRepository repo;
	
	public FacultyProfile save(FacultyProfile faculty) 
	{
		return repo.save(faculty);
	}

	public List<FacultyProfile> viewAll() 
	{
		return repo.findAll();
	}

	public Optional<FacultyProfile> viewById(int id) 
	{
		return repo.findById(id);
	}

	public void deleteFacultyById(int id)  
	{
		repo.deleteById(id);
	}

	public FacultyProfile findById(int id) 
	{
		return repo.findById(id).orElse(null);
	}

	public FacultyProfile updateStd(FacultyProfile faculty) 
	{
		return repo.save(faculty);
	}

	 
}
