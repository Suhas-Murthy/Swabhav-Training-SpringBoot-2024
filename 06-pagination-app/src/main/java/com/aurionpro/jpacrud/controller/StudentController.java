package com.aurionpro.jpacrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.jpacrud.entity.Student;
import com.aurionpro.jpacrud.service.StudentService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/student-app")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	
	@GetMapping("/students")
	public ResponseEntity<Page<Student>> getAllStudents(@RequestParam(required = false) String name , @RequestParam int pageNumber , @RequestParam int pageSize) {
		
		if(name!= null)
			return ResponseEntity.ok(studentService.getAllStudentsPage(name,pageSize,pageNumber));
		
		return ResponseEntity.ok(studentService.getAllStudents(pageNumber,pageSize));
	}
	
	@PostMapping("/students")
	public String addStudent(@RequestBody Student student) {
		ResponseEntity.ok(studentService.addStudent(student));
		return "student added";
	}
	
	@PutMapping("/students")
	public String updateStudent(@RequestBody Student student) {
		ResponseEntity.ok(studentService.updateStudent(student));
		return "student updated";
	}
	
	@GetMapping("/students/{rollnumber}")
	public ResponseEntity<Optional<Student>> getStudent(@PathVariable int rollnumber) {
		
		return ResponseEntity.ok( studentService.getStudent(rollnumber));
	}
	
	@GetMapping("/students-name/{name}")
	public ResponseEntity<Student> getStudentsByName(@PathVariable String name) {
		return ResponseEntity.ok(studentService.findByName(name));
	}
	
//	@GetMapping("/students")
//	public Page<Student> getAllStudents(int pageSize, int pageNumber) {
//		return studentService.getAllStudents(pageSize, pageNumber);
//	}
	
//	@GetMapping("/students/student-name")
//	public Page<Student> getStudentByName(@RequestParam String name,@RequestParam(required = false) int pageSize,@RequestParam int pageNumber){
//		return studentService.findByName(name, 0, 0);
//	}
//	@GetMapping("/students")
//	public ResponseEntity<Page<Student>> getAllStudents(@RequestParam(required = false) String name , @RequestParam int pageNumber , @RequestParam int pageSize) {
//		
//		if(name!= null)
//			return ResponseEntity.ok(studentService.getAllStudents(pageSize,pageNumber));
//		
//		return ResponseEntity.ok(studentService.getAllStudentsPage(name, pageSize, pageNumber));
//	}
	
	
}
