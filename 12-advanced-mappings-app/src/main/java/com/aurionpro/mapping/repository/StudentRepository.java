package com.aurionpro.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mapping.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
