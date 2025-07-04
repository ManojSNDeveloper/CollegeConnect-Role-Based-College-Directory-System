package com.manoj.cda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO 
{
    private int id;
    private String courseCode;
    private String courseName;
}
