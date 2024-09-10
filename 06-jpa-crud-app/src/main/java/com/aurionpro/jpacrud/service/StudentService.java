package com.aurionpro.jpacrud.service;

import java.util.List;
import java.util.Optional;

import com.aurionpro.jpacrud.entity.Student;

public interface StudentService {
	List<Student> getAllStudents();

	Optional<Student> getStudentById(int rollnumber);

	Student addStudent(Student student);
	Student updateStudent(Student student);
	List<Student> findByName(String name);
}
