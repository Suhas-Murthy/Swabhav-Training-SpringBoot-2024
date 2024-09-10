package com.aurionpro.bank.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Page<CustomerDto>> getAllCustomers(@RequestParam int pageNumber, @RequestParam int pageSize){
		return ResponseEntity.ok(customerService.getAllCustomers(pageNumber, pageSize));
	}
	
	@GetMapping("/customers/{customerId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable int customerId){
		return ResponseEntity.ok(customerService.getCustomerById(customerId));
	}
	
	
//	@PostMapping("/customers")
//	public ResponseEntity<CustomerDto> addNewCustomer(@Valid @RequestBody CustomerDto customerDto) {
//		return ResponseEntity.ok(customerService.addCustomer(customerDto));
//	}
	
	@PostMapping("/customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> addNewCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult result) {
	    if (result.hasErrors()) {
	        Map<String, String> errors = new HashMap<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errors.put(error.getField(), error.getDefaultMessage());
	        }
	        return ResponseEntity.badRequest().body(errors);
	    }
	    return ResponseEntity.ok(customerService.addCustomer(customerDto));
	}

	
	@PutMapping("/customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto) {
		return ResponseEntity.ok(customerService.updateCustomer(customerDto));
		
	}
}
