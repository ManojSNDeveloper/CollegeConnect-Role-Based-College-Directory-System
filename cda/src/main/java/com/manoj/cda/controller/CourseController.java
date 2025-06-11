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

import com.manoj.cda.entity.Course;
import com.manoj.cda.entity.FacultyProfile;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.response_structure.ResponseStructure;
import com.manoj.cda.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController 
{
	@Autowired
	private CourseService service ; 
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Course>> save(@RequestBody Course course)
	{
		return service.save(course);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Course>> updateCourse(@RequestBody Course courses)
	{
		return service.updateCourse(courses);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Course>>> viewAll()
	{
		return service.viewAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Optional<Course>>> viewById(@PathVariable int id)
	{
		return service.viewById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable int id)
	{
		return service.deleteById(id); 
	}
	
	@GetMapping("/branch/{branch}")
	public ResponseEntity<ResponseStructure<List<Course>>> findCourseByBranch(@PathVariable String branch)
	{
		return service.findByBranch(branch);  
	}
}