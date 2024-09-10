package com.aurionpro.mapping.service;

import java.util.List;

import org.springframework.data.domain.Page;
import com.aurionpro.mapping.dto.PageResponseDto;
import com.aurionpro.mapping.dto.InstructorDto;
import com.aurionpro.mapping.entity.Instructor;

public interface InstructorService {
	InstructorDto addInstructor(InstructorDto instructorDto);

	Page<InstructorDto> getAllInstructors(int pageNumber, int pageSize);
	
	InstructorDto getInstructorById(int instructorId);

	Instructor allocateCourses(int instructorId, List<Integer> courseIds);
	
	PageResponseDto<Instructor> getAllInstructorsPage(int pageSize, int pageNumber);
}
