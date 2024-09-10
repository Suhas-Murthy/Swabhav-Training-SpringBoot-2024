package com.aurionpro.mapping.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.aurionpro.mapping.dto.CourseDto;
import com.aurionpro.mapping.entity.Course;
import com.aurionpro.mapping.entity.Instructor;

public interface CourseService {
	CourseDto addCourse(CourseDto courseDto);

	Page<CourseDto> getAllCourses(int pageNumber, int pageSize);

	CourseDto getCourseById(int courseId);
	
	CourseDto allocateInstructors(int courseId, int instructorIds);
	
	CourseDto assignStudents(int courseId, List<Integer> students);
}
