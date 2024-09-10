package com.aurionpro.dboperations.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.dboperations.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentRepositoryImpl implements StudentRepository{

	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Student> getAllStudents() {
		TypedQuery<Student> query = manager.createQuery("select s from Student s", Student.class);
		return query.getResultList();
	}

	@Override
	public Student getStudent(int rollnumber) {
		// TODO Auto-generated method stub
		return manager.find(Student.class, rollnumber);
	}

	@Override
	@Transactional
	public void addStudent(Student student) {
		manager.persist(student);
		
	}

	@Override
	@Transactional
	public void updateStudent(Student student) {
		manager.merge(student);
		
		
	}

	@Override
	public List<Student> getAllStudentsByName(String name) {
		TypedQuery<Student> query = manager.createQuery("select s from Student s where s.name=:theName", Student.class);
		query.setParameter("theName",name);
		return query.getResultList();
	}

}
