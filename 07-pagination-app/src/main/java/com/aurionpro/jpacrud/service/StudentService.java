package com.aurionpro.jpacrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.aurionpro.jpacrud.dto.PageResponseDto;
import com.aurionpro.jpacrud.entity.Student;

public interface StudentService {
	
//	List<Student> getAllStudents();
	
	PageResponseDto<Student> getAllStudents(int pageNumber, int pageSize);
	
	Student addStudent(Student student);

	Student updateStudent(Student student);

	Optional<Student> getStudent(int rollnumber);
	
//	List<Student> findByName(String name);
	Student findByName(String name);
	
	PageResponseDto<Student> getAllStudentsPage(String name, int pageSize, int pageNumber);
	

}
