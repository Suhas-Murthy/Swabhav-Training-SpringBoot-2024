package com.aurionpro.pagination.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.pagination.dto.PageResponseDto;
import com.aurionpro.pagination.entity.Customer;
import com.aurionpro.pagination.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer-app")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
//		ResponseEntity.ok(studentService.addStudent(student));
		return ResponseEntity.ok(customerService.addCustomer(customer));
	}

	@PutMapping("/customers")
	public String updateCustomer(@RequestBody Customer customer) {
		ResponseEntity.ok(customerService.updateCustomer(customer));
		return "customer updated";
	}

	@GetMapping("/customers")
	public ResponseEntity<com.aurionpro.pagination.dto.PageResponseDto<Customer>> getAllCustomers(
			@RequestParam(required = false) String firstname, @RequestParam int pageNumber,
			@RequestParam int pageSize) {

		if (firstname != null)
			return ResponseEntity.ok(customerService.getAllCustomersPage(firstname, pageSize, pageNumber));

		return ResponseEntity.ok(customerService.getAllCustomers(pageNumber, pageSize));
	}
	@GetMapping("/customers/{customerid}")
	public ResponseEntity<Optional<Customer>> getCustomer(@PathVariable int customerid) {
		
		return ResponseEntity.ok(customerService.getCustomerbyCustomerid(customerid));
	}
}
