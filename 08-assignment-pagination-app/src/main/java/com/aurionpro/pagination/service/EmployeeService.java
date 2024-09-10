package com.aurionpro.pagination.service;

import org.springframework.data.domain.Page;

import com.aurionpro.pagination.entity.Employee;

public interface EmployeeService {
	Page<Employee> getAllEmployees(int pageNumber, int pageSize);

	Employee addEmployee(Employee employee);

	Employee updateEmployee(Employee employee);

	Page<Employee> getAllEmploeesByPage(String firstname, int pageSize, int pageNumber);

}

//	Employee deleteEmployee();
//	Page<Employee> getAllEmployeesBy