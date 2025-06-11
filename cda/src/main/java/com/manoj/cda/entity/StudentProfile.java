package com.manoj.cda.entity;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor 
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class StudentProfile 
{
    @Id
    private int userId; 

    @OneToOne
    @MapsId
    private User user;
    
    private String name;
    
    @ManyToOne
    private Department department;
    
    private String year;
    private String email;
    private Long phone;
    
    @ManyToMany
    private List<Course> courses;
} 
