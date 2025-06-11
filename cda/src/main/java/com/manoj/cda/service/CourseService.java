package com.manoj.cda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.manoj.cda.dao.CourseDao;
import com.manoj.cda.entity.Course;
import com.manoj.cda.entity.Department;
import com.manoj.cda.entity.FacultyProfile;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.entity.User;
import com.manoj.cda.repository.DepartmentRepository;
import com.manoj.cda.repository.FacultyProfileRepository;
import com.manoj.cda.repository.StudentProfileRepository;
import com.manoj.cda.repository.UserRepository;
import com.manoj.cda.response_structure.ResponseStructure;
import com.manoj.cda.util.Role;

@Service
public class CourseService 
{
	@Autowired
	private CourseDao dao ;

	 
	@Autowired
	private DepartmentRepository deptRepo;
	 
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Course>> save(Course course) 
	{
	    int deptId = course.getDepartment().getId();

	    // Load user and department from DB so they are managed entities
	    Department dept = deptRepo.findById(deptId).orElseThrow(() -> new RuntimeException("Department not found"));

	    

	    // Set managed references
	    course.setDepartment(dept);

	    Course saved = dao.saveStd(course);

	    ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Student Profile saved successfully").body(saved).build();
	    ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
	    return re;
	}


	public ResponseEntity<ResponseStructure<List<Course>>> viewAll() 
	{
		List<Course> all = dao.viewAll();
		ResponseStructure rs = ResponseStructure.builder()
	            .status(HttpStatus.OK.value())
	            .message("Faculty Profile saved successfully")
	            .body(all)
	            .build();
	    ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);

	    return re;
	}


	public ResponseEntity<ResponseStructure<List<Course>>> findByBranch(String branch) 
	{
		List<Course> el = dao.findByBranch(branch);
		ResponseStructure st = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Course Found Successfully").body(el).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(st);
		return re;
	}


	public ResponseEntity<ResponseStructure<Course>> updateCourse(Course courses) 
	{
	    int deptId = courses.getDepartment().getId();

	    // Check if student profile exists
	    Course existing = dao.findById(deptId);
	    if (existing == null) {
	        throw new RuntimeException("Department not found for update");
	    } 

	    // Load user and department
	    Department dept = deptRepo.findById(deptId)
	            .orElseThrow(() -> new RuntimeException("Department not found"));
  
	    // Update fields
	    existing.setDepartment(dept);
	    existing.setCourseName(courses.getCourseName());
	    existing.setCourseCode(courses.getCourseCode());

	    Course updated = dao.updateAcademic(existing);

	    ResponseStructure<Course> rs = ResponseStructure.<Course>builder()
	            .status(HttpStatus.OK.value())
	            .message("Student Profile updated successfully")
	            .body(updated)
	            .build();

	    return ResponseEntity.status(HttpStatus.OK).body(rs);
	}


	public ResponseEntity<ResponseStructure<Optional<Course>>> viewById(int id) 
	{
		Optional<Course> byId = dao.viewById(id);
		if(byId.isEmpty())
		{
			throw new RuntimeException("Invalid Course Id Unable to Find");
		}
		Course profile = byId.get();
		
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Course Found By ID Successfully").body(profile).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}


	public ResponseEntity<ResponseStructure<String>> deleteById(int id) 
	{
		Optional<Course> opt = dao.viewById(id); // viewById is above method
		if(opt.isEmpty())
		{
			throw new RuntimeException("Invalid Student Id Unable to delete");
		}
		dao.deleteStudentById(id);
		
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Course Delete By ID Successfully").body("Course Profile Deleted").build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}
	

}
