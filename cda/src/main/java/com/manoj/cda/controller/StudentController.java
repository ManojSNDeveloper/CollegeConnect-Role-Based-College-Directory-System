package com.manoj.cda.controller;

import java.util.List;
import java.util.Optional;

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

import com.manoj.cda.dto.FacultyProfileDTO;
import com.manoj.cda.dto.StudentProfileDTO;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.response_structure.ResponseStructure;
import com.manoj.cda.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController 
{
	@Autowired
	private StudentService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<StudentProfile>> saveStudent(@RequestBody StudentProfile student)
	{
		return service.saveStudent(student);
	}
	 
	@PutMapping
	public ResponseEntity<ResponseStructure<StudentProfile>> updateStudent(@RequestBody StudentProfile student)
	{
		return service.updateStudent(student);
	}
	
	@GetMapping("/search")
	public ResponseEntity<ResponseStructure<List<StudentProfileDTO>>> viewAll()
	{
		return service.viewAll();  
	}
	
	@GetMapping("/search-faculty-advisors")
	public ResponseEntity<ResponseStructure<List<FacultyProfileDTO>>> viewAllFacultyAdvisors()
	{
		return service.viewAllFacultyAdvisors();  
	}
	
	@GetMapping("/{id}/profile")
	public ResponseEntity<ResponseStructure<Optional<StudentProfile>>> viewById(@PathVariable int id)
	{
		return service.viewById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable int id)
	{
		return service.deleteById(id); 
	}
	
	@GetMapping("/year/{year}")
	public ResponseEntity<ResponseStructure<List<StudentProfile>>> findStudentByYear(@PathVariable String year)
	{
		return service.findByYear(year);  
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<ResponseStructure<List<StudentProfile>>> findStudentByName(@PathVariable String name)
	{
		return service.findByName(name);  
	}
	
	@GetMapping("/branch/{branch}")
	public ResponseEntity<ResponseStructure<List<StudentProfile>>> findStudentByBranch(@PathVariable String branch)
	{
		return service.findByBranch(branch);  
	}
}
