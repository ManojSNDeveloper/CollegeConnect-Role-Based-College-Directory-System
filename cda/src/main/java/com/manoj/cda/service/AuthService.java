package com.manoj.cda.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.manoj.cda.dto.LoginRequestDTO;
import com.manoj.cda.dto.LoginResponseDTO;
import com.manoj.cda.entity.AdministratorProfile;
import com.manoj.cda.entity.FacultyProfile;
import com.manoj.cda.entity.StudentProfile;
import com.manoj.cda.entity.User;
import com.manoj.cda.repository.AdministratorProfileRepository;
import com.manoj.cda.repository.FacultyProfileRepository;
import com.manoj.cda.repository.StudentProfileRepository;
import com.manoj.cda.repository.UserRepository;
import com.manoj.cda.response_structure.ResponseStructure;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private StudentProfileRepository studentRepo;

    @Autowired
    private FacultyProfileRepository facultyRepo;

    @Autowired
    private AdministratorProfileRepository adminRepo;

    public ResponseEntity<ResponseStructure<LoginResponseDTO>> login(LoginRequestDTO loginRequest) 
    {
        Optional<User> userOpt = userRepo.findByUsername(loginRequest.getUsername());

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Invalid username or password");
        }

        User user = userOpt.get();
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        LoginResponseDTO response = new LoginResponseDTO();
        response.setMessage("Login Successful");

        switch (user.getRole()) {
            case STUDENT:
                StudentProfile student = studentRepo.findById(user.getId()).orElseThrow(() -> new RuntimeException("Student Profile not found"));
                response.setProfile(student);
                break;

            case FACULTY:
                FacultyProfile faculty = facultyRepo.findById(user.getId()).orElseThrow(() -> new RuntimeException("Faculty Profile not found"));
                response.setProfile(faculty);
                break;

            case ADMIN:
                AdministratorProfile admin = adminRepo.findById(user.getId()).orElseThrow(() -> new RuntimeException("Admin Profile not found"));
                response.setProfile(admin);
                break;

            default:
                throw new RuntimeException("Unknown role: " + user.getRole());
        }

        ResponseStructure<LoginResponseDTO> rs = ResponseStructure.<LoginResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .message("User Logged in Successfully")
                .body(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(rs);
    }
     
}
