package com.manoj.cda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.manoj.cda.dao.AcademicSectionDao;
import com.manoj.cda.entity.AcademicSection;
import com.manoj.cda.entity.Course;
import com.manoj.cda.entity.Department;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.entity.User;
import com.manoj.cda.repository.CourseRepository;
import com.manoj.cda.repository.DepartmentRepository;
import com.manoj.cda.repository.StudentProfileRepository;
import com.manoj.cda.repository.UserRepository;
import com.manoj.cda.response_structure.ResponseStructure;
import com.manoj.cda.util.Role;
 

@Service
public class AcademicSectionService {

    @Autowired
    private AcademicSectionDao dao;

    @Autowired
    private StudentProfileRepository stdRepo;
    
    @Autowired
    private  UserRepository userRepo;
    
    @Autowired
    private  DepartmentRepository deptRepo;
    
    @Autowired
    private  CourseRepository courseRepo;

    public ResponseEntity<ResponseStructure<AcademicSection>> saveAcademic(AcademicSection section) {
        int studentId = section.getStdProfile().getUserId();

        // ✅ Step 1: Load managed entity
        StudentProfile profile = stdRepo.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found"));

        // ✅ Step 2: Set the managed profile
        section.setStdProfile(profile); // DO THIS FIRST

        // ❌ DO NOT manually call setStdId() when using @MapsId. Hibernate will use stdProfile.getUserId()

        // ✅ Step 3: Save
        AcademicSection saved = dao.saveAcademic(section);

        ResponseStructure<AcademicSection> rs = ResponseStructure.<AcademicSection>builder()
            .status(HttpStatus.OK.value())
            .message("Academic section saved successfully")
            .body(saved)
            .build();

        return ResponseEntity.ok(rs);
    }

	public ResponseEntity<ResponseStructure<List<AcademicSection>>> viewAll() 
	{ 
		List<AcademicSection> all = dao.viewAll();
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Academic Section Found Successfully").body(all).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}

	public ResponseEntity<ResponseStructure<Optional<AcademicSection>>> viewById(int id) 
	{
		Optional<AcademicSection> byId = dao.viewById(id);
		if(byId.isEmpty())
		{
			throw new RuntimeException("Invalid Academic Section Id Unable to Find");
		}
		AcademicSection profile = byId.get();
		
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Academic Section Found By ID Successfully").body(profile).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}
	
	public ResponseEntity<ResponseStructure<List<AcademicSection>>> findByBranch(String branch) {
	    List<AcademicSection> list = dao.findByBranch(branch);

	    ResponseStructure<List<AcademicSection>> structure = ResponseStructure.<List<AcademicSection>>builder()
	        .status(HttpStatus.OK.value())
	        .message(list.isEmpty() ? "No Academic Sections found for branch: " + branch : "Academic Section Found Successfully")
	        .body(list)
	        .build();

	    return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

	public ResponseEntity<ResponseStructure<AcademicSection>> updateAcademic(AcademicSection updatedSection) {
	    int studentId = updatedSection.getStdProfile().getUserId();

	    // Step 1: Check if the AcademicSection exists
	    AcademicSection existing = dao.findById(studentId)
	        .orElseThrow(() -> new RuntimeException("Academic Section not found for Student ID: " + studentId));

	    // Step 2: Ensure StudentProfile exists
	    StudentProfile profile = stdRepo.findById(studentId)
	        .orElseThrow(() -> new RuntimeException("Student not found for ID: " + studentId));

	    // Step 3: Update the fields (DO NOT replace the whole stdProfile — it’s managed by JPA)
	    existing.setCgpa(updatedSection.getCgpa());
	    existing.setAttendance(updatedSection.getAttendance());
	    existing.setStdProfile(profile);  // Optional but good to reattach managed entity

	    // Step 4: Save
	    AcademicSection saved = dao.updateAcademic(existing);

	    // Step 5: Build response
	    ResponseStructure<AcademicSection> response = ResponseStructure.<AcademicSection>builder()
	        .status(HttpStatus.OK.value())
	        .message("Academic section updated successfully")
	        .body(saved)
	        .build();

	    return ResponseEntity.ok(response);
	}


}
