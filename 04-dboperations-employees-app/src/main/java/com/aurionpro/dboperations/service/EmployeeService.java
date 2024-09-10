package com.aurionpro.dboperations.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.entity.Employee;

@Service
public interface EmployeeService {
	List<Employee> getAllEmployees();

}
