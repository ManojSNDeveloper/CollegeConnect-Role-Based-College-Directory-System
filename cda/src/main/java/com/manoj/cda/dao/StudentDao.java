package com.manoj.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.manoj.cda.entity.FacultyProfile;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.entity.User;
import com.manoj.cda.repository.FacultyProfileRepository;
import com.manoj.cda.repository.StudentProfileRepository;

@Repository
public class StudentDao 
{
	@Autowired
	private StudentProfileRepository repo;
	
	@Autowired
	private FacultyProfileRepository facRepo;
	
	public StudentProfile saveStd(StudentProfile student) 
	{ 
		 return repo.save(student);
	}

	public StudentProfile updateStd(StudentProfile student) 
	{
		return repo.save(student); 
	}
	
	public StudentProfile findById(int id) // for updating[PUT]
	{
	    return repo.findById(id).orElse(null);
	}

	
	public List<StudentProfile> viewAll()  
	{
		return repo.findAll();
	}

	public Optional<StudentProfile> viewById(int id) 
	{
		return repo.findById(id);
	}

 
	public User viewFacDetails() 
	{
		return (User) repo.findAll();
	}
 

	public void deleteStudentById(int id) 
	{
		repo.deleteById(id);
	}

	public List<StudentProfile> findByYear(String year) 
	{
		return repo.findByYear(year + "%"); 
	}

	public List<StudentProfile> findByName(String name) 
	{
		return repo.findByName(name + "%"); 
	}

	public List<FacultyProfile> viewAllFacultyAdvisors() 
	{
		return facRepo.findAll();
	}

	public List<StudentProfile> findByBranch(String branch) 
	{
		return repo.findByBranch(branch + "%"); 
	}

}
