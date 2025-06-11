package com.manoj.cda.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfileDTO 
{
    private String name;
    private DepartmentDTO department;
    private String year;
    private String email;
    private Long phone;
    private List<CourseDTO> courses;
}
