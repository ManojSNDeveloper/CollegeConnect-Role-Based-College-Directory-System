package com.manoj.cda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.manoj.cda.dao.AdministratorDao;
import com.manoj.cda.entity.AdministratorProfile;
import com.manoj.cda.entity.Course;
import com.manoj.cda.entity.Department;
import com.manoj.cda.entity.FacultyProfile;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.entity.User;
import com.manoj.cda.repository.CourseRepository;
import com.manoj.cda.repository.DepartmentRepository;
import com.manoj.cda.repository.FacultyProfileRepository;
import com.manoj.cda.repository.UserRepository;
import com.manoj.cda.response_structure.ResponseStructure;
import com.manoj.cda.util.Role;

@Service
public class AdministratorService {
	@Autowired
	private AdministratorDao dao;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private DepartmentRepository deptRepo;

	@Autowired
	private CourseRepository courseRepo;

	@Autowired
	private FacultyProfileRepository facultyRepo;

	public ResponseEntity<ResponseStructure<AdministratorProfile>> saveAdmin(AdministratorProfile admin) {
		int userId = admin.getUser().getId();
		int deptId = admin.getDepartment().getId();

		// Load user and department from DB so they are managed entities
		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Department dept = deptRepo.findById(deptId).orElseThrow(() -> new RuntimeException("Department not found"));

		// Check role
		if (user.getRole() != Role.ADMIN) {
			throw new RuntimeException("User is not a STUDENT");
		}

		// Set managed references
		admin.setUser(user);
		admin.setDepartment(dept);

		AdministratorProfile saved = dao.saveAdmin(admin);

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student Profile saved successfully").body(saved).build();

		return ResponseEntity.status(HttpStatus.OK.value()).body(rs);
	}

	public ResponseEntity<ResponseStructure<AdministratorProfile>> updateAcademic(AdministratorProfile admin) {
		int userId = admin.getUser().getId();
		int deptId = admin.getDepartment().getId();

		// Check if student profile exists
		AdministratorProfile existing = dao.findById(userId);
		if (existing == null) {
			throw new RuntimeException("AdministratorProfile not found for update");
		}

		// Load user and department
		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Department dept = deptRepo.findById(deptId).orElseThrow(() -> new RuntimeException("Department not found"));

		if (user.getRole() != Role.ADMIN) {
			throw new RuntimeException("User is not a ADMIN");
		}

		// Update fields
		existing.setUser(user); // already the same
		existing.setDepartment(dept);
		existing.setName(admin.getName());
		existing.setContactEmail(admin.getContactEmail());
		existing.setPhone(admin.getPhone());

		AdministratorProfile updated = dao.updateStd(existing);

		ResponseStructure<AdministratorProfile> rs = ResponseStructure.<AdministratorProfile>builder()
				.status(HttpStatus.OK.value()).message("Admin Profile updated successfully").body(updated).build();

		return ResponseEntity.status(HttpStatus.OK).body(rs);
	}

	public ResponseEntity<ResponseStructure<List<AdministratorProfile>>> viewAll() {
		List<AdministratorProfile> all = dao.viewAll();
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Admin Found Successfully").body(all).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}

	public ResponseEntity<ResponseStructure<Optional<AdministratorProfile>>> viewById(int id) {
		Optional<AdministratorProfile> byId = dao.viewById(id);
		if (byId.isEmpty()) {
			throw new RuntimeException("Invalid Admin Id Unable to Find");
		}
		AdministratorProfile profile = byId.get();

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Admin Found By ID Successfully").body(profile).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {
		Optional<AdministratorProfile> opt = dao.viewById(id); // viewById is above method
		if (opt.isEmpty()) {
			throw new RuntimeException("Invalid Admin Id Unable to delete");
		}
		dao.deleteById(id);

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Admin Delete By ID Successfully").body("Admin Profile Deleted").build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}

	// ------------------------- Student CRUD -----------------

	public ResponseEntity<ResponseStructure<StudentProfile>> saveStudent(StudentProfile student) {
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

		// Set managed references
		student.setUser(user);
		student.setDepartment(dept);
		student.setCourses(managedCourses); // ✅ Supports multiple courses
		StudentProfile saved = dao.saveStd(student);

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student Profile saved successfully").body(saved).build();

		return ResponseEntity.status(HttpStatus.OK.value()).body(rs);
	}

	public ResponseEntity<ResponseStructure<StudentProfile>> updateStudent(StudentProfile student) {
		int userId = student.getUser().getId();
		int deptId = student.getDepartment().getId();

		// Check if student profile exists
		StudentProfile existing = dao.findStdById(userId);
		if (existing == null) {
			throw new RuntimeException("User not found for update");
		}

		// Load user and department
		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Department dept = deptRepo.findById(deptId).orElseThrow(() -> new RuntimeException("Department not found"));

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

		ResponseStructure<StudentProfile> rs = ResponseStructure.<StudentProfile>builder().status(HttpStatus.OK.value())
				.message("Student Profile updated successfully").body(updated).build();

		return ResponseEntity.status(HttpStatus.OK).body(rs);
	}

	public ResponseEntity<ResponseStructure<List<StudentProfile>>> viewAllStudents() {
		List<StudentProfile> all = dao.viewAllStudents();
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student Found Successfully").body(all).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}

	public ResponseEntity<ResponseStructure<Optional<StudentProfile>>> viewStudentById(int studentId) {
		Optional<StudentProfile> byId = dao.viewStudentById(studentId);
		if (byId.isEmpty()) {
			throw new RuntimeException("Invalid Student Id Unable to Find");
		}
		StudentProfile profile = byId.get();

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student Found By ID Successfully").body(profile).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}

	public ResponseEntity<ResponseStructure<String>> deleteStudentById(int studentId) {
		Optional<StudentProfile> opt = dao.viewStudentById(studentId); // viewById is above method
		if (opt.isEmpty()) {
			throw new RuntimeException("Invalid event Id Unable to delete");
		}
		dao.deleteStudentById(studentId);

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student Delete By ID Successfully").body("Student Profile Deleted").build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}
 
	public ResponseEntity<ResponseStructure<List<StudentProfile>>> findByYear(String year) {
		List<StudentProfile> el = dao.findByYear(year);
		ResponseStructure st = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student Found Successfully").body(el).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(st);
		return re;
	}

	public ResponseEntity<ResponseStructure<List<StudentProfile>>> findByName(String name) {
		List<StudentProfile> el = dao.findByName(name);
		ResponseStructure st = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student Found Successfully").body(el).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(st);
		return re;
	}

	public ResponseEntity<ResponseStructure<List<StudentProfile>>> findByBranch(String branch) {
		List<StudentProfile> el = dao.findByBranch(branch);
		ResponseStructure st = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student Found Successfully").body(el).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(st);
		return re;
	}

	// ------------------------ Faculty CRUD -------------------------

	public ResponseEntity<ResponseStructure<FacultyProfile>> saveFaculty(FacultyProfile faculty) {
		int userId = faculty.getUser().getId();
		int deptId = faculty.getDepartment().getId();

		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User is Not Found in the DB"));
		Department dept = deptRepo.findById(deptId)
				.orElseThrow(() -> new RuntimeException("Department is Not Found in the DB"));

		if (user.getRole() != Role.FACULTY) {
			throw new RuntimeException("User is Not FACULTY");
		}

		faculty.setUser(user);
		faculty.setDepartment(dept);

		FacultyProfile saveFac = dao.saveFaculty(faculty);

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty Profile saved successfully").body(saveFac).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}

	public ResponseEntity<ResponseStructure<FacultyProfile>> updateFaculty(FacultyProfile faculty) {
		int userId = faculty.getUser().getId();
		int deptId = faculty.getDepartment().getId();

		// Check if student profile exists
		FacultyProfile existing = dao.findFacultyById(userId);
		if (existing == null) {
			throw new RuntimeException("FacultyProfile not found for update");
		}

		// Load user and department
		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Department dept = deptRepo.findById(deptId).orElseThrow(() -> new RuntimeException("Department not found"));

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

		ResponseStructure<FacultyProfile> rs = ResponseStructure.<FacultyProfile>builder().status(HttpStatus.OK.value())
				.message("Faculty Profile updated successfully").body(updated).build();

		return ResponseEntity.status(HttpStatus.OK).body(rs);
	}

	public ResponseEntity<ResponseStructure<List<FacultyProfile>>> viewAllFaculty() {
		List<FacultyProfile> all = dao.viewAllFaculty();
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty Profile saved successfully").body(all).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}

	public ResponseEntity<ResponseStructure<Optional<FacultyProfile>>> viewFacultyById(int facultyId) {
		Optional<FacultyProfile> byId = dao.viewFacultyById(facultyId);
		if (byId.isEmpty()) {
			throw new RuntimeException("Invalid Faculty Id Unable to Find");
		}
		FacultyProfile profile = byId.get();

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty Found By ID Successfully").body(profile).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}

	public ResponseEntity<ResponseStructure<String>> deleteFacultyById(int facultyId) {
		Optional<FacultyProfile> opt = dao.viewFacultyById(facultyId); // viewById is above method
		if (opt.isEmpty()) {
			throw new RuntimeException("Invalid Faculty Id Unable to delete");
		}
		dao.deleteFacultyById(facultyId);

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty Delete By ID Successfully").body("Student Profile Deleted").build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}

}
