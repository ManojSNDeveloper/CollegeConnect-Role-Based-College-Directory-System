package com.manoj.cda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manoj.cda.entity.AcademicSection;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.response_structure.ResponseStructure;
import com.manoj.cda.service.AcademicSectionService;

@RestController
@RequestMapping("/academic")
public class AcademicSectionController 
{
	@Autowired
	private AcademicSectionService service ;
	
	@PostMapping 
	public ResponseEntity<ResponseStructure<AcademicSection>> saveAcademic(@RequestBody AcademicSection section)
	{
		return service.saveAcademic(section);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<AcademicSection>> updateStudent(@RequestBody AcademicSection academic)
	{
		return service.updateAcademic(academic);
	}
	
	@GetMapping("/search")
	public ResponseEntity<ResponseStructure<List<AcademicSection>>> viewAll()
	{
		return service.viewAll();  
	}
	

	@GetMapping("/viewById/{id}")
	public ResponseEntity<ResponseStructure<Optional<AcademicSection>>> viewStudentById(@PathVariable int id)
	{
		return service.viewById(id);
	} 
	
	@GetMapping("/branch/{branch}")
	public ResponseEntity<ResponseStructure<List<AcademicSection>>> findStudentByBranch(@PathVariable String branch)
	{
		return service.findByBranch(branch);
	} 
	

}