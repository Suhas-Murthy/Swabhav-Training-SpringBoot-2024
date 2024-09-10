package com.aurionpro.mapping.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mapping.dto.CourseDto;
import com.aurionpro.mapping.entity.Course;
import com.aurionpro.mapping.entity.Instructor;
import com.aurionpro.mapping.entity.Student;
import com.aurionpro.mapping.repository.CourseRepository;
import com.aurionpro.mapping.repository.InstructorRepository;
import com.aurionpro.mapping.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepo;

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private InstructorRepository instructorRepo;

	public static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

	@Override
	public CourseDto addCourse(CourseDto courseDto) {
		Course course = toCourseMapper(courseDto);

		course = courseRepo.save(course);
		logger.info("Corse added Successfully" + course.getCourseId());
		return toCourseDtoMapper(course);
	}

	public Course toCourseMapper(CourseDto courseDto) {
		Course course = new Course();

		course.setName(courseDto.getName());
		course.setDuration(courseDto.getDuration());
		course.setFees(courseDto.getFees());
		return course;
	}

	public CourseDto toCourseDtoMapper(Course course) {
		CourseDto courseDto = new CourseDto();

		courseDto.setCourseId(course.getCourseId());
		courseDto.setName(course.getName());
		courseDto.setDuration(course.getDuration());
		courseDto.setFees(course.getFees());
		return courseDto;
	}

	@Override
	public Page<CourseDto> getAllCourses(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Course> coursePage = courseRepo.findAll(pageable);
		return coursePage.map(this::toCourseDtoMapper);
	}

	@Override
	public CourseDto getCourseById(int courseId) {
		Course course = null;
		Optional<Course> optionalCourse = courseRepo.findById(courseId);
		if (!optionalCourse.isPresent()) {
			logger.error("Course with id " + courseId + " not found");
			return null;
		}
		course = optionalCourse.get();
		return toCourseDtoMapper(course);
	}

	@Override
	public CourseDto allocateInstructors(int courseId, int instructorId) {
		Course dbCourse = toCourseMapper(getCourseById(courseId));
		dbCourse.setCourseId(courseId);

//		Optional<Instructor> optionalInstructor = instructorRepo.findById(instructorId);
		Instructor instructor = instructorRepo.findById(instructorId)
				.orElseThrow(() -> new NullPointerException("Instructor Not Found"));

		dbCourse.setInstructor(instructor);

		dbCourse = courseRepo.save(dbCourse);

		return toCourseDtoMapper(dbCourse);
	}

	@Override
	public CourseDto assignStudents(int courseId, List<Integer> studentIds) {
		Course dbCourse = courseRepo.findById(courseId).orElseThrow(() -> new NullPointerException("Course Not Found"));
		Set<Student> existingStudents = dbCourse.getStudents();
//		Set<Student> existingStudents = new HashSet<>(dbCourse.getStudents());

		if (existingStudents == null)
			existingStudents = new HashSet<>();

		Set<Student> studentsToAdd = new HashSet<>();
		studentIds.forEach(id -> {
			Student student = studentRepo.findById(id).orElseThrow(() -> new NullPointerException("Student not found"));
			Set<Course> courses = student.getCourses();
			if (courses == null)
				courses = new HashSet<>();

			courses.add(dbCourse);
			student.setCourses(courses);
			studentsToAdd.add(student);

		});

		existingStudents.addAll(studentsToAdd);
		dbCourse.setStudents(existingStudents);
		return toCourseDtoMapper(courseRepo.save(dbCourse));
	}

}
