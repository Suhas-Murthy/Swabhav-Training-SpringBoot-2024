package com.aurionpro.mapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mapping.dto.CourseDto;
import com.aurionpro.mapping.dto.InstructorDto;
import com.aurionpro.mapping.dto.StudentDto;
import com.aurionpro.mapping.entity.Course;
import com.aurionpro.mapping.service.CourseService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("studentapp")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@PostMapping("courses")
	public ResponseEntity<CourseDto> addNewCourse(@RequestBody CourseDto courseDto) {
		//TODO: process POST request
		
		return ResponseEntity.ok(courseService.addCourse(courseDto));
	}
	
	@GetMapping("courses")
	public ResponseEntity<Page<CourseDto>> getAllInstructors(@RequestParam int pageNumber,@RequestParam int pageSize){
		return ResponseEntity.ok(courseService.getAllCourses(pageNumber, pageSize));
		
	}

	
	@PostMapping("/courses/instructors")
	public ResponseEntity<CourseDto> assignInstructor(@RequestParam int courseId,@RequestParam int instructorId){
		return ResponseEntity.ok(courseService.allocateInstructors(courseId, instructorId));
	}
	
	@PutMapping("/courses/students")
	public ResponseEntity<CourseDto> assignStudents(@RequestParam int courseId, @RequestBody List<Integer> studentIds) {
	    return ResponseEntity.ok(courseService.assignStudents(courseId, studentIds));
	}
	
	@GetMapping("/courses/{courseId}")
	public ResponseEntity<CourseDto> getCourseById(@PathVariable int courseId) {
		return ResponseEntity.ok(courseService.getCourseById(courseId));
		
	}
}
