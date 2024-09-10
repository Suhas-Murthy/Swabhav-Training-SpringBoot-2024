package com.aurionpro.dboperations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.entity.Student;
import com.aurionpro.dboperations.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepository.getAllStudents();
	}

	@Override
	public Student getStudent(int rollnumber) {
		// TODO Auto-generated method stub
		return  studentRepository.getStudent(rollnumber);
	}

	@Override
	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		studentRepository.addStudent(student);
		
	}

	@Override
	public void updateStudent(Student student) {
		studentRepository.updateStudent(student);
	}

	@Override
	public List<Student> getAllStudentsByName(String name) {
		// TODO Auto-generated method stub
		return studentRepository.getAllStudentsByName(name);
	}

}
