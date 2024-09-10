package com.aurionpro.mapping.service;

import org.springframework.data.domain.Page;

import com.aurionpro.mapping.dto.EmployeeDto;


public interface EmployeeService {
	Page<EmployeeDto> getAllEmployees(int pageNumber, int pageSize);

	EmployeeDto addEmployee(EmployeeDto employeeDto);

	EmployeeDto updateEmployee(EmployeeDto employeeDto);

//	Page<EmployeeDto> getAllEmploeesByPage(String firstname, int pageSize, int pageNumber);

}
