package com.aurionpro.dboperations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dboperations.entity.Student;
import com.aurionpro.dboperations.service.StudentService;

@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	private StudentService studentService;

//	@GetMapping("/students")
//	public List<Student> getAllStudents() {
//		return studentService.getAllStudents();
//	}
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents(){
		return ResponseEntity.ok(studentService.getAllStudents());	
		
	}
	
	@GetMapping("/students/{rollnumber}")
	public ResponseEntity<Student> getStudent(@PathVariable int rollnumber){
		return ResponseEntity.ok(studentService.getStudent(rollnumber));
		
	}
	
	@PostMapping("/students")
	public String addStudent(@RequestBody Student student) {
		
		studentService.addStudent(student);
		return "Student Added";
		
	}
	
	@PostMapping("/updatestudent")
	public String updateStudent(@RequestBody Student student) {
		
		studentService.updateStudent(student);
		return "Student Updated";
		
	}
	
	@GetMapping("/students-name")
	public ResponseEntity<List<Student>> getStudentsByName(@RequestParam String name){
		return ResponseEntity.ok(studentService.getAllStudentsByName(name));
		
	}
	
	
}	

