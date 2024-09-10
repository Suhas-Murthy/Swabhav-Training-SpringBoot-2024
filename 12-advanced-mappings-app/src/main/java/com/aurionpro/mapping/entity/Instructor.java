package com.aurionpro.mapping.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "instructors")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Instructor {
	
	@Column(name = "instructorId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int instructorId;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "qualification")
	private String qualification;
	
	@OneToMany(mappedBy = "instructor", cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
	@JsonIgnore
	private List<Course> courses;
}
