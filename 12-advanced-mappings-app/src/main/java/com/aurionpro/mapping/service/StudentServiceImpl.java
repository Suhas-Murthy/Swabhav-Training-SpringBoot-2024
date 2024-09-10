package com.aurionpro.mapping.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mapping.dto.PageResponseDto;
import com.aurionpro.mapping.dto.StudentDto;
import com.aurionpro.mapping.entity.Address;
import com.aurionpro.mapping.entity.Course;
import com.aurionpro.mapping.entity.Instructor;
import com.aurionpro.mapping.entity.Student;
import com.aurionpro.mapping.repository.CourseRepository;
import com.aurionpro.mapping.repository.InstructorRepository;
import com.aurionpro.mapping.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private CourseRepository courseRepo;
	@Autowired
	private InstructorRepository instructorRepo;

//	@Override
//	public Student addStudent(Student student) {
//		// TODO Auto-generated method stub
//		return studentRepo.save(student);
//	}
	@Override
	public StudentDto addStudent(Student student) {
		// TODO Auto-generated method stub
		return toStudentDtoMapper(studentRepo.save(student));
	}

	@Override
	public Page<Student> getAllStudents(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		return studentRepo.findAll(pageable);
	}

	@Override
	public Address getStudentAddressByRollnumber(int rollNumber) {
		Optional<Student> studentDb = studentRepo.findById(rollNumber);
		Address address = studentDb.get().getAddress();
		return address;
	}

	@Override
	public Address updateStudentAddressByRollNumber(int rollNumber, Address address) {

		Optional<Student> studentDb = studentRepo.findById(rollNumber);
		Student student1 = studentDb.get();
		Address currentAddress = student1.getAddress();

		currentAddress.setAreaName(address.getAreaName());
		currentAddress.setBuildingName(address.getBuildingName());
		currentAddress.setCity(address.getCity());
		currentAddress.setPincode(address.getPincode());
		student1.setAddress(currentAddress);
		return studentRepo.save(student1).getAddress();
	}

	private StudentDto toStudentDtoMapper(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setName(student.getName());
		studentDto.setRollNumber(student.getRollNumber());
		studentDto.setAge(student.getAge());
		return studentDto;
	}

	private Student toStudentMapper(StudentDto studentDto) {
		Student student = new Student();
		student.setName(studentDto.getName());
		student.setAge(studentDto.getAge());
		if (studentDto.getRollNumber() > 0)
			student.setRollNumber(studentDto.getRollNumber());
		return student;
	}

	@Override
	public StudentDto getStudentByRollNumber(int rollNumber) {
		Optional<Student> optionalStudent = studentRepo.findById(rollNumber);
		if (!optionalStudent.isPresent())
			return null;
		Student student = optionalStudent.get();
		return toStudentDtoMapper(student);

	}

	@Override
	public PageResponseDto<StudentDto> getAllStudentsDto(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Student> studentPage = studentRepo.findAll(pageable);
		List<StudentDto> studentDtoList = new ArrayList<StudentDto>();
		StudentDto studentDto = new StudentDto();

		for (Student student : studentPage.getContent()) {
			studentDto = toStudentDtoMapper(student);
			studentDtoList.add(studentDto);
		}

		PageResponseDto<StudentDto> studentpageDto = new PageResponseDto<>();
		studentpageDto.setTotalPages(studentPage.getTotalPages());
		studentpageDto.setTotalElements(studentPage.getTotalElements());
		studentpageDto.setSize(studentPage.getSize());
		studentpageDto.setContent(studentDtoList);
		studentpageDto.setLastPage(studentPage.isLast());

		return studentpageDto;
	}

	@Override
	public StudentDto assignCourses(int rollNumber, List<Integer> courseIds) {
		Student dbStudent = studentRepo.findById(rollNumber).orElseThrow(()->new NullPointerException("Student Not Found"));
		Set<Course> existingCourses = dbStudent.getCourses();
		
		if(existingCourses==null) {
			existingCourses = new HashSet<>();
		}
		
		Set<Course> coursesToAdd = new HashSet<>();
		courseIds.forEach(id -> {
	        Course course = courseRepo.findById(id).orElseThrow(() -> new NullPointerException("Course not found"));
	        Set<Student> students = course.getStudents();
	        if(students == null)
	            students = new HashSet<>();

	        students.add(dbStudent);
	        course.setStudents(students);

	        coursesToAdd.add(course);
	    });

	    existingCourses.addAll(coursesToAdd);
	    dbStudent.setCourses(existingCourses);

	    return toStudentDtoMapper(studentRepo.save(dbStudent));
	}

	@Override
	public Set<StudentDto> getAllStudentsByInstructorId(int instructorId) {
		// TODO Auto-generated method stub
		  Instructor instructor = instructorRepo.findById(instructorId) 
				    .orElseThrow(() -> new NullPointerException("Instructor not found")); 
				 
				  List<Course> courses = instructor.getCourses(); 
				 
				  Set<Student> studentsSet = new HashSet<>(); 
				 
				  for (Course course : courses) { 
				   studentsSet.addAll(course.getStudents()); 
				  } 
				 
				  Set<StudentDto> studentDtos = new HashSet<>(); 
				  for (Student student : studentsSet) { 
				   studentDtos.add(toStudentDtoMapper(student)); 
				  } 
				 
				  return studentDtos; 
	}

}
