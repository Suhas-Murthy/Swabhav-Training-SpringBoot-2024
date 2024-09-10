package com.aurionpro.mapping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mapping.entity.Employee;
import com.aurionpro.mapping.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Override
	public Page<Employee> getAllEmployees(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		return employeeRepo.findAll(pageable);
	}

	@Override
	public Employee addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepo.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepo.save(employee);
	}

	@Override
	public Page<Employee> getAllEmploeesByPage(String firstname, int pageSize, int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return employeeRepo.findByFirstname(firstname, pageable);
	}

}
