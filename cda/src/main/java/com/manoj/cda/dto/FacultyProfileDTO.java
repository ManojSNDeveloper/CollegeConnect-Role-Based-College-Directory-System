package com.manoj.cda.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyProfileDTO 
{
	private String name;
    private DepartmentDTO department;
    private String email;
    private String phone;
}
