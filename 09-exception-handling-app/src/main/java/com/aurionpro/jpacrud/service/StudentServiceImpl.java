package com.aurionpro.jpacrud.service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.aurionpro.jpacrud.dto.PageResponseDto;
import com.aurionpro.jpacrud.entity.Student;
import com.aurionpro.jpacrud.exceptions.StudentDoesNotExistsException;
import com.aurionpro.jpacrud.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;

//	@Override
//	public List<Student> getAllStudents() {
//		
//		return studentRepo.findAll();
//		
//	}

	@Override
	public PageResponseDto getAllStudents(int pageNumber, int pageSize) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Student> studentPage = studentRepo.findAll(pageable);
		PageResponseDto studentPageDto = new PageResponseDto();
		studentPageDto.setTotalPages(studentPage.getTotalPages());
		studentPageDto.setTotalElements(studentPage.getTotalElements());
		studentPageDto.setSize(studentPage.getSize());
		studentPageDto.setContent(studentPage.getContent());
		studentPageDto.setLastPage(studentPage.isLast());

		return studentPageDto;

	}

	@Override
	public Student addStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepo.save(student);
	}

	@Override
	public PageResponseDto getAllStudentsPage(String name, int pageSize, int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		 Page<Student> studentPage= studentRepo.findByName(name, pageable);
		 PageResponseDto studentPageDto = new PageResponseDto();
			studentPageDto.setTotalPages(studentPage.getTotalPages());
			studentPageDto.setTotalElements(studentPage.getTotalElements());
			studentPageDto.setSize(studentPage.getSize());
			studentPageDto.setContent(studentPage.getContent());
		 
		 return studentPageDto;

	}

	@Override
	public Student updateStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepo.save(student);
	}

	@Override
	public Student getStudentByRollNumber(int rollnumber) {
		
		Student student = null;
		Optional<Student> dbStudent= studentRepo.findById(rollnumber);
		
		if(!dbStudent.isPresent())
			throw new StudentDoesNotExistsException();
		
		return dbStudent.get();
	}

//	@Override
//	public List<Student> findByName(String name) {
//		// TODO Auto-generated method stub
//		return studentRepo.findByName(name);
//	}

	@Override
	public Student findByName(String name) {
		// TODO Auto-generated method stub
		return studentRepo.findByName(name);
	}

}