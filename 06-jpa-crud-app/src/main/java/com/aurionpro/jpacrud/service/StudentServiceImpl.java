package com.aurionpro.jpacrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.jpacrud.entity.Student;
import com.aurionpro.jpacrud.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public List<Student> getAllStudents() {

		return studentRepo.findAll();
	}

	
	
	@Override
	public Student addStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepo.save(student);
	}



	@Override
	public Optional<Student> getStudentById(int rollnumber) {
		// TODO Auto-generated method stub
		return studentRepo.findById(rollnumber);
	}



	@Override
	public Student updateStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepo.save(student);
	}



	@Override
	public List<Student> findByName(String name) {
		// TODO Auto-generated method stub
		return studentRepo.findByName(name);
	}

}
