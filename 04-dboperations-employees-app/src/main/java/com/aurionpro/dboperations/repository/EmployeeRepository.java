package com.aurionpro.dboperations.repository;

import java.util.List;


import com.aurionpro.dboperations.entity.Employee;

public interface EmployeeRepository {

	
	List<Employee> getAllEmployees();
}
