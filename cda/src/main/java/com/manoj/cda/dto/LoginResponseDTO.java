package com.manoj.cda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private String message;
    private Object profile; // Will hold StudentProfile, FacultyProfile, or AdminProfile
}
