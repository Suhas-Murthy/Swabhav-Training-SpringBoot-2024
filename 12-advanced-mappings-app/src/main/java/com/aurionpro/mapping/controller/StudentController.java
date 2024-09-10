package com.aurionpro.mapping.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mapping.dto.PageResponseDto;
import com.aurionpro.mapping.dto.StudentDto;
import com.aurionpro.mapping.entity.Address;
import com.aurionpro.mapping.entity.Student;
import com.aurionpro.mapping.service.StudentService;

@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
//	@PostMapping("/students")
//	public ResponseEntity<Student> addNewStudent(@RequestBody Student student){
//		return ResponseEntity.ok(studentService.addStudent(student));
//	}
	@PostMapping("/students")
	public ResponseEntity<StudentDto> addNewStudent(@RequestBody Student student){
		return ResponseEntity.ok(studentService.addStudent(student));
	}
	
//	@GetMapping("/students")
//	public ResponseEntity<Page<Student>> getAllStudents(@RequestParam int pageSize, int pageNumber){
//		return ResponseEntity.ok(studentService.getAllStudents(pageNumber, pageSize));
//		
//	}
	@GetMapping("/students")
	public ResponseEntity<PageResponseDto<StudentDto>> getAllStudentsDto(@RequestParam int pageSize, @RequestParam int pageNumber){
		return ResponseEntity.ok(studentService.getAllStudentsDto(pageNumber, pageSize));
		
	}
	
	@GetMapping("/students/address")
	public ResponseEntity<Address> getStudentAddressByRollnumber(@RequestParam int rollNumber){
		return ResponseEntity.ok(studentService.getStudentAddressByRollnumber(rollNumber));
	}
	
	@PutMapping("/students/address")
	public ResponseEntity<Address> updateStudentAddressByRollNumber(@RequestParam int rollNumber, @RequestBody Address address){
		return ResponseEntity.ok(studentService.updateStudentAddressByRollNumber(rollNumber, address));
		
	}
	
	@PutMapping("/students/courses")
	public ResponseEntity<StudentDto> assignCourses(@RequestParam int rollNumber, @RequestBody List<Integer> courseIds) {
	    return ResponseEntity.ok(studentService.assignCourses(rollNumber, courseIds));
	}
	
	@GetMapping("/students/instructor") 
	 public ResponseEntity<Set<StudentDto>> getAllStudentsByInstructorId(@RequestParam int instructorId){ 
	  return ResponseEntity.ok(studentService.getAllStudentsByInstructorId(instructorId)); 
	 }
	
}
