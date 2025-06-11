package com.manoj.cda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.manoj.cda.dao.StudentDao;
import com.manoj.cda.dto.CourseDTO;
import com.manoj.cda.dto.DepartmentDTO;
import com.manoj.cda.dto.FacultyProfileDTO;
import com.manoj.cda.dto.StudentProfileDTO;
import com.manoj.cda.entity.Course;
import com.manoj.cda.entity.Department;
import com.manoj.cda.entity.FacultyProfile;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.entity.User;
import com.manoj.cda.repository.CourseRepository;
import com.manoj.cda.repository.DepartmentRepository;
import com.manoj.cda.repository.FacultyProfileRepository;
import com.manoj.cda.repository.StudentProfileRepository;
import com.manoj.cda.repository.UserRepository;
import com.manoj.cda.response_structure.ResponseStructure;
import com.manoj.cda.util.Role;

@Service
public class StudentService 
{
	@Autowired
	private StudentDao dao;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private DepartmentRepository deptRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private StudentProfileRepository Repo;
	
	@Autowired
	private FacultyProfileRepository facultyRepo;

	
	public ResponseEntity<ResponseStructure<StudentProfile>> saveStudent(StudentProfile student) 
	{
	    int userId = student.getUser().getId();
	    int deptId = student.getDepartment().getId();

	    // Load user and department from DB so they are managed entities
	    User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
	    Department dept = deptRepo.findById(deptId).orElseThrow(() -> new RuntimeException("Department not found"));
	    
	    // Check role
	    if (user.getRole() != Role.STUDENT) {
	        throw new RuntimeException("User is not a STUDENT");
	    }

	    // Convert all course IDs into managed entities
	    List<Course> managedCourses = student.getCourses().stream()
	        .map(course -> courseRepo.findById(course.getId())
	            .orElseThrow(() -> new RuntimeException("Course not found with id: " + course.getId())))
	        .collect(Collectors.toList());
	    
	   /* List<FacultyProfile> managedFaculty = student.getFacultyAdvisors().stream()
	    		.map(faculty -> facultyRepo.findById(faculty.getId())
	    				.orElseThrow(() -> new RuntimeException("Faculty not found with id: " + faculty.getId())))
	    		.collect(Collectors.toList()) ; */ 

	    
	    // Set managed references
	    student.setUser(user);
	    student.setDepartment(dept);
	    student.setCourses(managedCourses); // ✅ Supports multiple courses
	    //student.setFacultyAdvisors(managedFaculty);
	    
	    StudentProfile saved = dao.saveStd(student);

	    ResponseStructure rs = ResponseStructure.builder()
	        .status(HttpStatus.OK.value())
	        .message("Student Profile saved successfully")
	        .body(saved)
	        .build();

	    return ResponseEntity.status(HttpStatus.OK.value()).body(rs);
	}
 
	public ResponseEntity<ResponseStructure<StudentProfile>> updateStudent(StudentProfile student) {
	    int userId = student.getUser().getId();
	    int deptId = student.getDepartment().getId();

	    // Check if student profile exists
	    StudentProfile existing = dao.findById(userId);
	    if (existing == null) {
	        throw new RuntimeException("StudentProfile not found for update");
	    } 

	    // Load user and department
	    User user = userRepo.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	    Department dept = deptRepo.findById(deptId)
	            .orElseThrow(() -> new RuntimeException("Department not found"));

	    if (user.getRole() != Role.STUDENT) {
	        throw new RuntimeException("User is not a STUDENT");
	    }

	    // Fetch managed course entities
	    List<Course> managedCourses = student.getCourses().stream()
	            .map(course -> courseRepo.findById(course.getId())
	                    .orElseThrow(() -> new RuntimeException("Course not found: " + course.getId())))
	            .collect(Collectors.toList());

	    // Update fields
	    existing.setUser(user); // already the same
	    existing.setDepartment(dept);
	    existing.setCourses(managedCourses);
	    existing.setName(student.getName());
	    existing.setYear(student.getYear());
	    existing.setEmail(student.getEmail());
	    existing.setPhone(student.getPhone());

	    StudentProfile updated = dao.updateStd(existing);

	    ResponseStructure<StudentProfile> rs = ResponseStructure.<StudentProfile>builder()
	            .status(HttpStatus.OK.value())
	            .message("Student Profile updated successfully")
	            .body(updated)
	            .build();

	    return ResponseEntity.status(HttpStatus.OK).body(rs);
	}

	
	public ResponseEntity<ResponseStructure<List<StudentProfileDTO>>> viewAll() 
	{
	    List<StudentProfile> allStudents = dao.viewAll();

	    List<StudentProfileDTO> dtos = allStudents.stream().map(student -> 
	    { 
	     // Convert Department
	     Department dept = student.getDepartment();
	     DepartmentDTO  deptDTO = new DepartmentDTO(dept.getId(), dept.getName());
	     
	        // Convert Courses
	     List<CourseDTO> courseDTOs = student.getCourses().stream()
         .map(course -> new CourseDTO(course.getId(), course.getCourseCode(), course.getCourseName()))
         .collect(Collectors.toList());
    
	     // Build StudentProfileDTO 
	     return new StudentProfileDTO( 
	            student.getName(),
	            deptDTO, 
	            student.getYear(),
	            student.getEmail(),
	            student.getPhone(), 
	            courseDTOs 
	        );
	    }).collect(Collectors.toList());

	    ResponseStructure<List<StudentProfileDTO>> rs = ResponseStructure.<List<StudentProfileDTO>>builder()
	        .status(HttpStatus.OK.value())
	        .message("Student Found Successfully")
	        .body(dtos)
	        .build();

	    return ResponseEntity.status(HttpStatus.OK).body(rs);
	}


	public ResponseEntity<ResponseStructure<Optional<StudentProfile>>> viewById(int id) 
	{
		Optional<StudentProfile> byId = dao.viewById(id);
		if(byId.isEmpty())
		{
			throw new RuntimeException("Invalid Student Id Unable to Find");
		}
		StudentProfile profile = byId.get();
		
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Student Found By ID Successfully").body(profile).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}


	public ResponseEntity<ResponseStructure<String>> deleteById(int id) 
	{
		Optional<StudentProfile> opt = dao.viewById(id); // viewById is above method
		if(opt.isEmpty())
		{
			throw new RuntimeException("Invalid Student Id Unable to delete");
		}
		dao.deleteStudentById(id);
		
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Student Delete By ID Successfully").body("Student Profile Deleted").build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}


	public ResponseEntity<ResponseStructure<List<StudentProfile>>> findByYear(String year) 
	{
		List<StudentProfile> el = dao.findByYear(year);
		ResponseStructure st = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Student Found Successfully").body(el).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(st);
		return re;
	}


	public ResponseEntity<ResponseStructure<List<StudentProfile>>> findByName(String name) 
	{
		List<StudentProfile> el = dao.findByName(name);
		ResponseStructure st = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Student Found Successfully").body(el).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(st);
		return re;
	}


	public ResponseEntity<ResponseStructure<List<FacultyProfileDTO>>> viewAllFacultyAdvisors() 
	{
		List<FacultyProfile> allFaculty = dao.viewAllFacultyAdvisors();

	    List<FacultyProfileDTO> dtos = allFaculty.stream().map(faculty -> 
	    { 
	     // Convert Department
	     Department dept = faculty.getDepartment();
	     DepartmentDTO  deptDTO = new DepartmentDTO(dept.getId(), dept.getName());
	     
	      
    
	     // Build StudentProfileDTO 
	     return new FacultyProfileDTO( 
	            faculty.getName(),
	            deptDTO, 
	            faculty.getEmail(),
	            faculty.getPhone() 
	        );
	    }).collect(Collectors.toList());

	    ResponseStructure<List<FacultyProfileDTO>> rs = ResponseStructure.<List<FacultyProfileDTO>>builder()
	        .status(HttpStatus.OK.value())
	        .message("Faculty Found Successfully")
	        .body(dtos)
	        .build();

	    return ResponseEntity.status(HttpStatus.OK).body(rs);
	}


	public ResponseEntity<ResponseStructure<List<StudentProfile>>> findByBranch(String branch) 
	{
		List<StudentProfile> el = dao.findByBranch(branch);
		ResponseStructure st = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Student Found Successfully").body(el).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(st);
		return re;
	}



	/*
	 	📦 Summary of Objects Used
		Object								Purpose
		StudentProfile					Entity fetched from DB
		StudentProfileDTO				Data Transfer Object sent to the frontend
		Department						Entity (part of student)
		DepartmentDTO					Simplified version for the frontend
		Course							Entity (part of student)
		CourseDTO						Simplified version for the frontend
		ResponseStructure<T>			Custom wrapper for consistent API responses
		ResponseEntity					Spring’s standard way to return HTTP responses
	 */

	 

}
