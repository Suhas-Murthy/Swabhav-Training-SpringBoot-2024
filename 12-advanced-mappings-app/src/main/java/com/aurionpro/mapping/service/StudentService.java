package com.aurionpro.mapping.service;

import org.springframework.data.domain.Page;

import com.aurionpro.mapping.dto.PageResponseDto;
import com.aurionpro.mapping.dto.StudentDto;
import com.aurionpro.mapping.entity.Address;
import com.aurionpro.mapping.entity.Course;
import com.aurionpro.mapping.entity.Student;

import java.util.*;

public interface StudentService {

//	Student addStudent(Student student);
	StudentDto addStudent(Student student);

	Page<Student> getAllStudents(int pageNumber, int pageSize);
//	PageResponseDto<Student> getAllStudents(int pageNumber, int pageSize);
	
	Address getStudentAddressByRollnumber(int rollNumber);
	Address updateStudentAddressByRollNumber(int rollNumber, Address address);
	
	StudentDto getStudentByRollNumber(int rollNumber);
	
	PageResponseDto<StudentDto> getAllStudentsDto(int pageNumber, int pageSize);
	
	StudentDto assignCourses(int rollNumber, List<Integer> courses);

	Set<StudentDto> getAllStudentsByInstructorId(int instructorId);
}
