package com.aurionpro.pagination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.pagination.entity.Employee;
import com.aurionpro.pagination.service.EmployeeService;

@RestController
@RequestMapping("employeeapp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employees")
	public String addEmployee(@RequestBody Employee employee) {
		ResponseEntity.ok(employeeService.addEmployee(employee));
		return "employee added";
	}
	
	@PutMapping("/employees")
	public String updateEmployee(@RequestBody Employee employee) {
		ResponseEntity.ok(employeeService.updateEmployee(employee));
		return "employee updated";
	}

	@GetMapping("/employees")
	public ResponseEntity<Page<Employee>> getAllEmployees(@RequestParam(required = false) String firstname,
			 @RequestParam int pageNumber, @RequestParam int pageSize) {

		if (firstname != null)
			return ResponseEntity.ok(employeeService.getAllEmploeesByPage(firstname, pageSize, pageNumber));

		return ResponseEntity.ok(employeeService.getAllEmployees(pageNumber, pageSize));
	}

}
