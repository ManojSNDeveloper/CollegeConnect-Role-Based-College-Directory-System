package com.manoj.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.manoj.cda.entity.AdministratorProfile;
import com.manoj.cda.entity.FacultyProfile;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.repository.AdministratorProfileRepository;
import com.manoj.cda.repository.FacultyProfileRepository;
import com.manoj.cda.repository.StudentProfileRepository;

@Repository
public class AdministratorDao 
{
	@Autowired
	private AdministratorProfileRepository adminRepo ;
	
	@Autowired
	private StudentProfileRepository stdRepo;
	
	@Autowired
	private FacultyProfileRepository facRepo;
	
	//--------------- Admin CRUD -------------------------------
	
	public AdministratorProfile saveAdmin(AdministratorProfile admin) 
	{
		return adminRepo.save(admin);
	}
	
	public AdministratorProfile updateStd(AdministratorProfile existing) 
	{
		return adminRepo.save(existing);
	}
	
	public AdministratorProfile findById(int id) // for updating[PUT]
	{
	    return adminRepo.findById(id).orElse(null);
	}

	public List<AdministratorProfile> viewAll() 
	{
		return adminRepo.findAll();
	}
	
	public Optional<AdministratorProfile> viewById(int id) 
	{
		return adminRepo.findById(id);
	}
	
	public void deleteById(int id) 
	{
		adminRepo.deleteById(id);
	}

	// -------------- Student CRUD ------------------------------
	
	public StudentProfile saveStd(StudentProfile student) 
	{ 
		 return stdRepo.save(student);
	}

	public StudentProfile findStdById(int id) // for updating[PUT]
	{
	    return stdRepo.findById(id).orElse(null);
	}
	
	public StudentProfile updateStd(StudentProfile student) 
	{
		return stdRepo.save(student); 
	}
	
	public List<StudentProfile> viewAllStudents() 
	{
		return stdRepo.findAll();
	}

	public Optional<StudentProfile> viewStudentById(int studentId) 
	{
		return stdRepo.findById(studentId);
	}

	public void deleteStudentById(int studentId) 
	{
		stdRepo.deleteById(studentId);
	}
	
	public List<StudentProfile> findByYear(String year) 
	{
		return stdRepo.findByYear(year + "%"); 
	}

	public List<StudentProfile> findByName(String name) 
	{
		return stdRepo.findByName(name + "%"); 
	}
 

	public List<StudentProfile> findByBranch(String branch) 
	{
		return stdRepo.findByBranch(branch + "%"); 
	}
	
	// ------------------- Faculty CRUD ---------------------------

	public FacultyProfile saveFaculty(FacultyProfile faculty) 
	{
		return facRepo.save(faculty);
	}

	public List<FacultyProfile> viewAllFaculty() 
	{
		return facRepo.findAll();
	}
	
	public FacultyProfile findFacultyById(int id) 
	{
		return facRepo.findById(id).orElse(null);
	}

	public FacultyProfile updateStd(FacultyProfile faculty) 
	{
		return facRepo.save(faculty);
	}


	public Optional<FacultyProfile> viewFacultyById(int facultyId) 
	{
		return facRepo.findById(facultyId);
	}

	public void deleteFacultyById(int facultyId) 
	{
		facRepo.deleteById(facultyId);
	}

}
