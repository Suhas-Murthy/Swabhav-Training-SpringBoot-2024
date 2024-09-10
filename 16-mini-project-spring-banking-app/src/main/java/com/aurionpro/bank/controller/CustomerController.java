package com.aurionpro.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.bank.dto.CustomerDto;
import com.aurionpro.bank.service.CustomerService;

@RestController
@RequestMapping("bankapp")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/customers")
	public ResponseEntity<Page<CustomerDto>> getAllCustomers(@RequestParam int pageNumber, @RequestParam int pageSize){
		return ResponseEntity.ok(customerService.getAllCustomers(pageNumber, pageSize));
	}
	
	@PostMapping("/customers")
	public ResponseEntity<CustomerDto> addNewCustomer(@RequestBody CustomerDto customerDto) {
		return ResponseEntity.ok(customerService.addCustomer(customerDto));
	}
	
	@PutMapping("/customers")
	public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) {
		return ResponseEntity.ok(customerService.updateCustomer(customerDto));
		
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable int customerId){
		return ResponseEntity.ok(customerService.getCustomerById(customerId));
	}
}
