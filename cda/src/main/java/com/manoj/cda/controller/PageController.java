package com.manoj.cda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 
@Controller
public class PageController 
{
 
	@GetMapping("/")
	public String home() {
	    return "forward:/index.html"; // Or change to any welcome page
	}
	
    @GetMapping("/admin-course")
    public String adminCourse() {
        return "forward:/admin-course.html";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard() {
        return "forward:/admin-dashboard.html";
    }

    @GetMapping("/admin-department")
    public String adminDepartment() {
        return "forward:/admin-department.html";
    }

    @GetMapping("/admin-login")
    public String adminLogin() {
        return "forward:/admin-login.html";
    }

    @GetMapping("/admin-profile")
    public String adminProfile() {
        return "forward:/admin-profile.html";
    }

    @GetMapping("/admin-register")
    public String adminRegister() {
        return "forward:/admin-register.html";
    }

    @GetMapping("/admin-setting")
    public String adminSetting() {
        return "forward:/admin-setting.html";
    }

    @GetMapping("/admin-update")
    public String adminUpdate() {
        return "forward:/admin-update.html";
    }

    @GetMapping("/admin-user-admin")
    public String adminUserAdmin() {
        return "forward:/admin-user-admin.html";
    }

    @GetMapping("/admin-user-faculty")
    public String adminUserFaculty() {
        return "forward:/admin-user-faculty.html";
    }

    @GetMapping("/admin-user-student")
    public String adminUserStudent() {
        return "forward:/admin-user-student.html";
    }

    @GetMapping("/faculty-batch")
    public String facultyBatch() {
        return "forward:/faculty-batch.html";
    }

    @GetMapping("/faculty-course")
    public String facultyCourse() {
        return "forward:/faculty-course.html";
    }

    @GetMapping("/faculty-dashboard")
    public String facultyDashboard() {
        return "forward:/faculty-dashboard.html";
    }

    @GetMapping("/faculty-login")
    public String facultyLogin() {
        return "forward:/faculty-login.html";
    }

    @GetMapping("/faculty-profile")
    public String facultyProfile() {
        return "forward:/faculty-profile.html";
    }

    @GetMapping("/faculty-register")
    public String facultyRegister() {
        return "forward:/faculty-register.html";
    }

    @GetMapping("/faculty-setting")
    public String facultySetting() {
        return "forward:/faculty-setting.html";
    }

    @GetMapping("/faculty-update")
    public String facultyUpdate() {
        return "forward:/faculty-update.html";
    }

    @GetMapping("/index")
    public String index() {
        return "forward:/index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "forward:/login.html";
    }

    @GetMapping("/student-academic")
    public String studentAcademic() {
        return "forward:/student-academic.html";
    }

    @GetMapping("/studentdashboard")
    public String studentDashboard() {
        return "forward:/studentdashboard.html";
    }

    @GetMapping("/student-faculty")
    public String studentFaculty() {
        return "forward:/student-faculty.html";
    }

    @GetMapping("/student-login")
    public String studentLogin() {
        return "forward:/student-login.html";
    }

    @GetMapping("/student-profile")
    public String studentProfile() {
        return "forward:/student-profile.html";
    }

    @GetMapping("/student-register")
    public String studentRegister() {
        return "forward:/student-register.html";
    }

    @GetMapping("/student-search")
    public String studentSearch() {
        return "forward:/student-search.html";
    }

    @GetMapping("/student-setting")
    public String studentSetting() {
        return "forward:/student-setting.html";
    }

    @GetMapping("/student-update")
    public String studentUpdate() {
        return "forward:/student-update.html";
    }

    @GetMapping("/viewAll")
    public String viewAll() {
        return "forward:/viewAll.html";
    }
}


