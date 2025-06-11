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

import com.manoj.cda.entity.FacultyProfile;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.response_structure.ResponseStructure;
import com.manoj.cda.service.FacultyService;

@RestController
@RequestMapping("/faculty")
public class FacultyController 
{
	@Autowired
	private FacultyService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<FacultyProfile>> save(@RequestBody FacultyProfile faculty)
	{
		return service.save(faculty);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<FacultyProfile>> updateStudent(@RequestBody FacultyProfile faculty)
	{
		return service.updateFaculty(faculty);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<FacultyProfile>>> viewAll()
	{
		return service.viewAll();
	}
	
	@GetMapping("/{id}/profile")
	public ResponseEntity<ResponseStructure<Optional<FacultyProfile>>> viewById(@PathVariable int id)
	{
		return service.viewById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable int id)
	{
		return service.deleteById(id); 
	}
}
