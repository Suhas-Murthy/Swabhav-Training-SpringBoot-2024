package com.aurionpro.dboperations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dboperations.entity.Employee;
import com.aurionpro.dboperations.service.EmployeeService;

@RestController
@RequestMapping("/employeeapp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("employees")
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
}
