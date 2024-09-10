package com.aurionpro.mapping.entity;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="courses")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Course {
	@Column(name="courseId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	@Column(name="name")
	private String name;
	@Column(name="duration")
	private int duration;
	@Column(name="fees")
	private double fees;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="instructorId")
	private Instructor instructor;
	
	
	@ManyToMany(mappedBy = "courses")
	private Set<Student> students;
	
	 @Override 
	    public int hashCode() { 
	        return Objects.hash(courseId); // Use only immutable fields 
	    } 
	     
	    @Override 
	    public boolean equals(Object o) { 
	        if (this == o) return true; 
	        if (o == null || getClass() != o.getClass()) return false; 
	        Course course = (Course) o; 
	        return Objects.equals(courseId, course.courseId); // Use only immutable fields 
	    } 
}
