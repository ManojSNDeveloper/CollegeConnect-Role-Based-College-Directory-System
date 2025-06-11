package com.manoj.cda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.manoj.cda.dao.DepartmentDao;
import com.manoj.cda.entity.AdministratorProfile;
import com.manoj.cda.entity.Course;
import com.manoj.cda.entity.Department;
import com.manoj.cda.entity.FacultyProfile;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.entity.User;
import com.manoj.cda.repository.DepartmentRepository;
import com.manoj.cda.response_structure.ResponseStructure;
import com.manoj.cda.util.Role;

@Service
public class DepartmentService 
{
	@Autowired
	private DepartmentDao dao;
	
	@Autowired
	private DepartmentRepository deptRepo;
	
	public ResponseEntity<ResponseStructure<Department>> saveDept(Department dept) 
	{
		Department save = dao.saveDept(dept);
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Department Saved Successfully").body(save).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}

	public ResponseEntity<ResponseStructure<List<Department>>> viewAll() 
	{
		List<Department> all = dao.viewAll();
		ResponseStructure rs = ResponseStructure.builder()
	            .status(HttpStatus.OK.value())
	            .message("Faculty Profile saved successfully")
	            .body(all)
	            .build();
	    ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);

	    return re;
	}


	public ResponseEntity<ResponseStructure<Department>> updateDepartment(Department department) 
	{
		int deptId = department.getId();

	    // Fetch existing department
	    Department existingDept = dao.getDepartmentById(deptId);
	    if (existingDept == null) {
	        throw new RuntimeException("Department not found with ID: " + deptId);
	    }

	    // Update fields
	    existingDept.setName(department.getName());
	    existingDept.setDescription(department.getDescription());

	    // Save updated department
	    Department updatedDept = dao.updateDepartment(existingDept);

	    // Build response
	    ResponseStructure<Department> rs = ResponseStructure.<Department>builder()
	            .status(HttpStatus.OK.value())
	            .message("Department Profile updated successfully")
	            .body(updatedDept)
	            .build();

	    return ResponseEntity.status(HttpStatus.OK).body(rs);	
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) 
	{
		Optional<Department> opt = dao.viewById(id); // viewById is above method
		if(opt.isEmpty())
		{
			throw new RuntimeException("Invalid Dept Id Unable to delete");
		}
		dao.deleteById(id);
		
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Admin Delete By ID Successfully").body("Admin Profile Deleted").build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}
}
