package com.aurionpro.jpacrud.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.jpacrud.dto.PageResponseDto;
import com.aurionpro.jpacrud.entity.Student;


public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	
//	List<Student> findByName(String name);
	
	Student findByName(String name);
	
	Page<Student> findByName(String name , Pageable pageable);

}
