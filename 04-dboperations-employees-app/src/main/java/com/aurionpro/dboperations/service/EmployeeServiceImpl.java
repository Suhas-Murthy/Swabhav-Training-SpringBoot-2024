package com.aurionpro.dboperations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.entity.Employee;
import com.aurionpro.dboperations.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepo;
	
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.getAllEmployees();
	}

}
