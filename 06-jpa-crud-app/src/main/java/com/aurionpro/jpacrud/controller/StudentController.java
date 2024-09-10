package com.aurionpro.jpacrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}
	
	@GetMapping("/students/{rollnumber}")
	public Optional<Student> getStudentById(@PathVariable int rollnumber){
		return studentService.getStudentById(rollnumber);
	}
	
	@PostMapping("/students")
	@Transactional
	public String addStudent(@RequestBody Student student) {
		studentService.addStudent(student);
		return "Added Successfully";
	}
	
	@PutMapping("/updatestudents")
	@Transactional
	public String updateStudent(@RequestBody Student student) {
		studentService.updateStudent(student);
		return "Updated Successfully";
	}
	
	@GetMapping("/students/student-name")
	public List<Student> getStudentByName(@RequestParam String name){
		return studentService.findByName(name);
	}
	
	
}
