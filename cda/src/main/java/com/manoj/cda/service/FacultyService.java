package com.manoj.cda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.manoj.cda.dao.FacultyDao;
import com.manoj.cda.entity.Course;
import com.manoj.cda.entity.Department;
import com.manoj.cda.entity.FacultyProfile;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.entity.User;
import com.manoj.cda.repository.DepartmentRepository;
import com.manoj.cda.repository.UserRepository;
import com.manoj.cda.response_structure.ResponseStructure;
import com.manoj.cda.util.Role;

@Service
public class FacultyService 
{
	@Autowired
	private FacultyDao dao;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private DepartmentRepository deptRepo;

	public ResponseEntity<ResponseStructure<FacultyProfile>> save(FacultyProfile faculty) 
	{
		int userId = faculty.getUser().getId();
		int deptId = faculty.getDepartment().getId();
		
		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User is Not Found in the DB"));
		Department dept = deptRepo.findById(deptId).orElseThrow(() -> new RuntimeException("Department is Not Found in the DB"));
		
		if(user.getRole() != Role.FACULTY) 
		{
			throw new RuntimeException("User is Not FACULTY");
		}
		
		faculty.setUser(user);
		faculty.setDepartment(dept);
		
		FacultyProfile saveFac = dao.save(faculty);
		
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Faculty Profile saved successfully").body(saveFac).build();
	    ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
	    return re;
	}

	public ResponseEntity<ResponseStructure<List<FacultyProfile>>> viewAll() 
	{
		List<FacultyProfile> all = dao.viewAll();
		ResponseStructure rs = ResponseStructure.builder()
	            .status(HttpStatus.OK.value())
	            .message("Faculty Profile saved successfully")
	            .body(all)
	            .build();
	    ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);

	    return re;	
	}

	public ResponseEntity<ResponseStructure<Optional<FacultyProfile>>> viewById(int id) 
	{
		Optional<FacultyProfile> byId = dao.viewById(id);
		if(byId.isEmpty())
		{
			throw new RuntimeException("Invalid Faculty Id Unable to Find");
		}
		FacultyProfile profile = byId.get();
		
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Faculty Found By ID Successfully").body(profile).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) 
	{
		Optional<FacultyProfile> opt = dao.viewById(id); // viewById is above method
		if(opt.isEmpty())
		{
			throw new RuntimeException("Invalid Faculty Id Unable to delete");
		}
		dao.deleteFacultyById(id);
		
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Faculty Delete By ID Successfully").body("Student Profile Deleted").build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}

	public ResponseEntity<ResponseStructure<FacultyProfile>> updateFaculty(FacultyProfile faculty) 
	{
		    int userId = faculty.getUser().getId();
		    int deptId = faculty.getDepartment().getId();

		    // Check if student profile exists
		    FacultyProfile existing = dao.findById(userId);
		    if (existing == null) {
		        throw new RuntimeException("FacultyProfile not found for update");
		    } 

		    // Load user and department
		    User user = userRepo.findById(userId)
		            .orElseThrow(() -> new RuntimeException("User not found"));
		    Department dept = deptRepo.findById(deptId)
		            .orElseThrow(() -> new RuntimeException("Department not found"));

		    if (user.getRole() != Role.FACULTY) {
		        throw new RuntimeException("User is not a FACULTY");
		    }

		     
		    // Update fields
		    existing.setUser(user); // already the same
		    existing.setDepartment(dept);
		    existing.setName(faculty.getName());
		    existing.setEmail(faculty.getEmail());
		    existing.setPhone(faculty.getPhone());
		    existing.setOfficeHours(faculty.getOfficeHours());

		    FacultyProfile updated = dao.updateStd(existing);

		    ResponseStructure<FacultyProfile> rs = ResponseStructure.<FacultyProfile>builder()
		            .status(HttpStatus.OK.value())
		            .message("Faculty Profile updated successfully")
		            .body(updated)
		            .build();
 
		    return ResponseEntity.status(HttpStatus.OK).body(rs);
	}
}
