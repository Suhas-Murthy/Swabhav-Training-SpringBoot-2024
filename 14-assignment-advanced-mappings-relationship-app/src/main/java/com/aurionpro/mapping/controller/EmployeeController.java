package com.aurionpro.mapping.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mapping.dto.EmployeeDto;
import com.aurionpro.mapping.entity.Employee;
import com.aurionpro.mapping.service.EmployeeService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/employeeapp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employees")
	public ResponseEntity<Object> addEmployee(@Valid @RequestBody EmployeeDto employeeDto, BindingResult result) {
		 if (result.hasErrors()) {
	            Map<String, String> errors = new HashMap<>();
	            for (FieldError error : result.getFieldErrors()) {
	                errors.put(error.getField(), error.getDefaultMessage());
	            }
	            return ResponseEntity.badRequest().body(errors);
	        }
	        employeeService.addEmployee(employeeDto);
	        return ResponseEntity.ok("Employee added successfully");
//		ResponseEntity.ok(employeeService.addEmployee(employee));
//		return "employee added";
	}
	
	@PutMapping("/employees")
	public ResponseEntity<Object> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto, BindingResult result) {
		   if (result.hasErrors()) {
	            Map<String, String> errors = new HashMap<>();
	            for (FieldError error : result.getFieldErrors()) {
	                errors.put(error.getField(), error.getDefaultMessage());
	            }
	            return ResponseEntity.badRequest().body(errors);
	        }
	        employeeService.updateEmployee(employeeDto);
	        return ResponseEntity.ok("Employee updated successfully");
//		ResponseEntity.ok(employeeService.updateEmployee(employee));
//		return "employee updated";
	}

	@GetMapping("/employees")
	public ResponseEntity<Page<EmployeeDto>> getAllEmployees(@RequestParam(required = false) String firstname,
			 @RequestParam int pageNumber, @RequestParam int pageSize) {

//		if (firstname != null)
//			return ResponseEntity.ok(employeeService.getAllEmploeesByPage(firstname, pageSize, pageNumber));

		return ResponseEntity.ok(employeeService.getAllEmployees(pageNumber, pageSize));
	}
}
