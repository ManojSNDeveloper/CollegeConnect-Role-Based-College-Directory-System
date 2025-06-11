package com.manoj.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.manoj.cda.entity.Course;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.repository.CourseRepository;

@Repository
public class CourseDao 
{
	@Autowired
	private CourseRepository repo ; 
	
	public Course saveStd(Course course) 
	{
		return repo.save(course);
	}

	public List<Course> viewAll() 
	{
		return repo.findAll();
	}

	public List<Course> findByBranch(String branch) 
	{
		return repo.findByBranch(branch + "%"); 
	}
	
	public Course findById(int id) // for updating[PUT]
	{
	    return repo.findById(id).orElse(null);
	}
	
	

	public Optional<Course> viewById(int id) 
	{
		return repo.findById(id);
	}

	public void deleteStudentById(int id) 
	{
		repo.deleteById(id);
	}

	public Course updateAcademic(Course courses) 
	{
		return repo.save(courses); 
	}
	
}
