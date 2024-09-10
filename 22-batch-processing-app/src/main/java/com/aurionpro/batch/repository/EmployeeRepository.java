package com.aurionpro.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.batch.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
