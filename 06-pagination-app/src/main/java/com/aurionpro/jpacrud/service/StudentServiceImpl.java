package com.aurionpro.jpacrud.service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.aurionpro.jpacrud.entity.Student;
import com.aurionpro.jpacrud.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;

//	@Override
//	public Page<Student> getAllStudents(int pageSize, int pageNumber) {
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		return studentRepo.findAll(pageable);
//	}

//	@Override
//	public Page<Student> findByName(String name, int pageSize, int pageNumber) {
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		return studentRepo.findAll(pageable);
//	}

//	@Override
//	public Student findByName(String name) {
//		// TODO Auto-generated method stub
//		return studentRepo.findByName(name);
//	}
//
//	@Override
//	public Page<Student> getAllStudentsPage(String name, int pageSize, int pageNumber) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public Page<Student> getAllStudents(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return studentRepo.findAll(pageable);
	}

	@Override
	public Student addStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepo.save(student);
	}
	
	@Override
	public Page<Student> getAllStudentsPage(String name, int pageSize, int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return studentRepo.findByName(name,pageable);
				
	}


	@Override
	public Student updateStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepo.save(student);
	}


	@Override
	public Optional<Student> getStudent(int rollnumber) {
		// TODO Auto-generated method stub
		return studentRepo.findById(rollnumber);
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
