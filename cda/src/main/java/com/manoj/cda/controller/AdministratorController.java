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

import com.manoj.cda.entity.AdministratorProfile;
import com.manoj.cda.entity.FacultyProfile;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.response_structure.ResponseStructure;
import com.manoj.cda.service.AdministratorService;
import com.manoj.cda.service.StudentService;

@RestController
@RequestMapping("/api/admin")
public class AdministratorController {
	@Autowired
	private AdministratorService service;

	// ------------------ Admin CRUD -------------------

	@PostMapping("/save-admin")
	public ResponseEntity<ResponseStructure<AdministratorProfile>> saveAdmin(@RequestBody AdministratorProfile admin) {
		return service.saveAdmin(admin);
	}

	@PutMapping("/update-admin")
	public ResponseEntity<ResponseStructure<AdministratorProfile>> updateAdmin(
			@RequestBody AdministratorProfile admin) {
		return service.updateAcademic(admin);
	}

	@GetMapping("/viewAll-admin")
	public ResponseEntity<ResponseStructure<List<AdministratorProfile>>> viewAll() {
		return service.viewAll();
	}

	@GetMapping("/viewAdmin-byId/{id}")
	public ResponseEntity<ResponseStructure<Optional<AdministratorProfile>>> viewById(@PathVariable int id) {
		return service.viewById(id);
	}

	@DeleteMapping("/delete-admin/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable int id) {
		return service.deleteById(id);
	}

	// ----------------- Student CRUD ---------------------

	@PostMapping("/save-students")
	public ResponseEntity<ResponseStructure<StudentProfile>> saveStudent(@RequestBody StudentProfile student) {
		return service.saveStudent(student);
	}

	@PutMapping("/update-students")
	public ResponseEntity<ResponseStructure<StudentProfile>> updateStudent(@RequestBody StudentProfile student) {
		return service.updateStudent(student);
	}

	@GetMapping("/viewAll-students")
	public ResponseEntity<ResponseStructure<List<StudentProfile>>> viewAllStudents() {
		return service.viewAllStudents();
	}

	@GetMapping("/viewStudent-byId/{studentId}")
	public ResponseEntity<ResponseStructure<Optional<StudentProfile>>> viewStudentById(@PathVariable int studentId) {
		return service.viewStudentById(studentId);
	}

	@DeleteMapping("/delete-student/{studentId}")
	public ResponseEntity<ResponseStructure<String>> deleteStudentById(@PathVariable int studentId) {
		return service.deleteStudentById(studentId);
	}

	@GetMapping("/year/{year}")
	public ResponseEntity<ResponseStructure<List<StudentProfile>>> findStudentByYear(@PathVariable String year) {
		return service.findByYear(year);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<ResponseStructure<List<StudentProfile>>> findStudentByName(@PathVariable String name) {
		return service.findByName(name);
	}

	@GetMapping("/branch/{branch}")
	public ResponseEntity<ResponseStructure<List<StudentProfile>>> findStudentByBranch(@PathVariable String branch) {
		return service.findByBranch(branch);
	}

	// --------------------- Faculty CRUD ----------------------------

	@PostMapping("/save-faculty")
	public ResponseEntity<ResponseStructure<FacultyProfile>> saveFaculty(@RequestBody FacultyProfile faculty) {
		return service.saveFaculty(faculty);
	}

	@PutMapping("/update-faculty")
	public ResponseEntity<ResponseStructure<FacultyProfile>> updateStudent(@RequestBody FacultyProfile faculty) {
		return service.updateFaculty(faculty);
	}

	@GetMapping("/viewAll-faculty")
	public ResponseEntity<ResponseStructure<List<FacultyProfile>>> viewAllFaculty() {
		return service.viewAllFaculty();
	}

	@GetMapping("/viewFaculty-byId/{facultyId}")
	public ResponseEntity<ResponseStructure<Optional<FacultyProfile>>> viewFacultyById(@PathVariable int facultyId) {
		return service.viewFacultyById(facultyId);
	}

	@DeleteMapping("/delete-faculty/{facultyId}")
	public ResponseEntity<ResponseStructure<String>> deleteFacultyById(@PathVariable int facultyId) {
		return service.deleteFacultyById(facultyId);
	}

}
