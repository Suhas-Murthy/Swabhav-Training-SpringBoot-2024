package com.aurionpro.jpacrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.aurionpro.jpacrud.dto.PageResponseDto;
import com.aurionpro.jpacrud.entity.Student;

public interface StudentService {
	
//	List<Student> getAllStudents();
	
	PageResponseDto<Student> getAllStudents(int pageNumber, int pageSize);
	
	PageResponseDto<Student> getAllStudentsPage(String name, int pageSize, int pageNumber);
	
	
	
	Student addStudent(Student student);

	Student updateStudent(Student student);

	Student getStudentByRollNumber(int rollnumber);
	
//	List<Student> findByName(String name);
	Student findByName(String name);
}
	
