package com.manoj.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.manoj.cda.entity.Department;
import com.manoj.cda.repository.DepartmentRepository;

@Repository
public class DepartmentDao 
{
	@Autowired
	private DepartmentRepository repo;

	public Department saveDept(Department dept) 
	{
		return repo.save(dept);
	}

	public List<Department> viewAll() 
	{
		return repo.findAll();
	}
	
	public Department getDepartmentById(int id) 
	{
        return repo.findById(id).orElse(null);
    }

    public Department updateDepartment(Department dept) 
    {
        return repo.save(dept);
    }

	public Optional<Department> viewById(int id) 
	{
		return repo.findById(id);
	}

	public void deleteById(int id) 
	{
		repo.deleteById(id);
	}
}