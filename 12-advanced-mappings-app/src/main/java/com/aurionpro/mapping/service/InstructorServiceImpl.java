package com.aurionpro.mapping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mapping.dto.InstructorDto;
import com.aurionpro.mapping.dto.PageResponseDto;
import com.aurionpro.mapping.entity.Course;
import com.aurionpro.mapping.entity.Instructor;
import com.aurionpro.mapping.repository.CourseRepository;
import com.aurionpro.mapping.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	private InstructorRepository instructorRepo;
	@Autowired
	private CourseRepository courseRepo;

	@Override
	public InstructorDto addInstructor(InstructorDto instructorDto) {
		Instructor instructor = toInstructorMapper(instructorDto);
		instructor = instructorRepo.save(instructor);

		return toInstructorDtoMapper(instructor);
	}

	public InstructorDto toInstructorDtoMapper(Instructor instructor) {
		InstructorDto instructorDto = new InstructorDto();
		instructorDto.setEmail(instructor.getEmail());
		instructorDto.setName(instructor.getName());
		instructorDto.setInstructorId(instructor.getInstructorId());
		instructorDto.setQualification(instructor.getQualification());
		return instructorDto;

	}

	public Instructor toInstructorMapper(InstructorDto instructorDto) {
		Instructor instructor = new Instructor();
		instructor.setEmail(instructorDto.getEmail());
		instructor.setName(instructorDto.getName());
		instructor.setQualification(instructorDto.getQualification());
		return instructor;

	}

	@Override
	public Page<InstructorDto> getAllInstructors(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Instructor> instructorPage = instructorRepo.findAll(pageable);
		return instructorPage.map(this::toInstructorDtoMapper);
	}

	@Override
	public InstructorDto getInstructorById(int instructorId) {
		Optional<Instructor> optionalInstructor = instructorRepo.findById(instructorId);
		if(!optionalInstructor.isPresent())
			return null;
		Instructor dbInstructor = optionalInstructor.get();
		return toInstructorDtoMapper(dbInstructor);
	}

	@Override 
	public Instructor allocateCourses(int instructorId, List<Integer> courseIds) { 
	    Instructor dbInstructor = toInstructorMapper(getInstructorById(instructorId)); 
	    dbInstructor.setInstructorId(instructorId); 
	    List<Course> dbCourses = dbInstructor.getCourses(); 
	    if (dbCourses == null) { 
	        dbCourses = new ArrayList<>(); 
	    } 
	    List<Course> coursesToAdd = new ArrayList<>(); 
	     
	    courseIds.forEach(courseId -> { 
	        Optional<Course> optionalCourse = courseRepo.findById(courseId); 
	        if (!optionalCourse.isPresent()) { 
	            throw new NullPointerException("Course does not exists"); 
	        } 
	        Course course = optionalCourse.get(); 
	        course.setInstructor(dbInstructor); 
	        coursesToAdd.add(course); 
	    }); 
	 
	    dbCourses.addAll(coursesToAdd); 
	    dbInstructor.setCourses(dbCourses); 
	 
	    return instructorRepo.save(dbInstructor); 
	}

	@Override
	public PageResponseDto<Instructor> getAllInstructorsPage(int pageSize, int pageNumber) {
		
			Pageable pageable = PageRequest.of(pageNumber, pageSize);

			Page<Instructor> instructorPage = instructorRepo.findAll(pageable);
			 PageResponseDto instructorPageDto = new PageResponseDto();
			 instructorPageDto.setTotalPages(instructorPage.getTotalPages());
			 instructorPageDto.setTotalElements(instructorPage.getTotalElements());
			 instructorPageDto.setSize(instructorPage.getSize());
			 instructorPageDto.setContent(instructorPage.getContent());
			 instructorPageDto.setLastPage(instructorPage.isLast());
			 
			 return instructorPageDto;

		
	}


}
