package com.manoj.cda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manoj.cda.entity.Department;
import com.manoj.cda.entity.FacultyProfile;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.entity.User;
import com.manoj.cda.response_structure.ResponseStructure;
import com.manoj.cda.service.DepartmentService;
import com.manoj.cda.service.UserService;

@RestController
@RequestMapping("/dept")
public class DepartmentController 
{
	@Autowired
	private DepartmentService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Department>> saveDepartment(@RequestBody Department dept)
	{
		return service.saveDept(dept);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Department>>> viewAll()
	{
		return service.viewAll();
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Department>> updateDepartment(@RequestBody Department department)
	{
		return service.updateDepartment(department);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable int id)
	{
		return service.deleteById(id); 
	}
}
