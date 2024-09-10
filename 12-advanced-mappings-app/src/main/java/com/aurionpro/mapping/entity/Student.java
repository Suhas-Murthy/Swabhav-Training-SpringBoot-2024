package com.aurionpro.mapping.entity;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "students")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Student {
	@Column(name = "rollNumber")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rollNumber;
	@Column(name = "name")
	private String name;
	@Column(name = "age")
	private int age;

	// Foreign Key of address is attached like this making one to one relationship
	// with annotation
//	@OneToOne(cascade= {CascadeType.DETACH,CascadeType.PERSIST})========> if we want any specific cascade then do like this
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address-name")
	private Address address;

	@ManyToMany
	@JoinTable(name = "student-course", joinColumns = @JoinColumn(name = "rollNumber"), inverseJoinColumns = @JoinColumn(name = "courseId"))
	private Set<Course> courses;
	//we are using set here yo get used with set and to avoid duplication as such duplication doesnot occur because we write the code as such

	 @Override 
	    public int hashCode() { 
	        return Objects.hash(rollNumber); // Use only immutable fields 
	    } 
	     
	    @Override 
	    public boolean equals(Object o) { 
	        if (this == o) return true; 
	        if (o == null || getClass() != o.getClass()) return false; 
	        Student student = (Student) o; 
	        return Objects.equals(rollNumber, student.rollNumber); // Use only immutable fields 
	    } 
}
