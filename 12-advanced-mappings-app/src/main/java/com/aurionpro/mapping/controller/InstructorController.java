package com.aurionpro.mapping.controller;

import java.util.List;

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

import com.aurionpro.mapping.dto.InstructorDto;
import com.aurionpro.mapping.dto.PageResponseDto;
import com.aurionpro.mapping.entity.Instructor;
import com.aurionpro.mapping.service.InstructorService;

@RestController
@RequestMapping("/studentapp")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	@PostMapping("/instructors")
	public ResponseEntity<InstructorDto> addNewInstructor(@RequestBody InstructorDto instructorDto){
		return ResponseEntity.ok(instructorService.addInstructor(instructorDto));
	}
	
	@GetMapping("/instructors")
	public ResponseEntity<Page<InstructorDto>> getAllInstructors(@RequestParam int pageNumber,@RequestParam int pageSize){
		return ResponseEntity.ok(instructorService.getAllInstructors(pageNumber, pageSize));
		
	}
	
	@GetMapping("/instructorspage")
	public ResponseEntity<PageResponseDto<Instructor>> getAllInstructorsPage(@RequestParam int pageNumber,@RequestParam int pageSize){
		return ResponseEntity.ok(instructorService.getAllInstructorsPage(pageSize, pageNumber));
		
	}
	@GetMapping("/instructors/{instructorId}")
	public ResponseEntity<InstructorDto> getAllInstructors(@PathVariable int instructorId){
		return ResponseEntity.ok(instructorService.getInstructorById(instructorId));
		
	}
	
	@PutMapping("/instructors/courses")
	public ResponseEntity<Instructor> allocateCourses(@RequestParam int instructorId, @RequestBody List<Integer> courseIds){
		return ResponseEntity.ok(instructorService.allocateCourses(instructorId, courseIds));
	}
	
	
}
