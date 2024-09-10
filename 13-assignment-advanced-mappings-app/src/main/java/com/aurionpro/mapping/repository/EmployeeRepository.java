package com.aurionpro.mapping.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mapping.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	Page<Employee> findByFirstname(String firstname, Pageable pageable);

}
