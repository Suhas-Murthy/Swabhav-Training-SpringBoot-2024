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

import com.aurionpro.bank.dto.AdminDto;
import com.aurionpro.bank.service.AdminService;

@RestController
@RequestMapping("bankapp")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admins")
	public ResponseEntity<Page<AdminDto>> getAllCustomers(@RequestParam int pageNumber, @RequestParam int pageSize){
		return ResponseEntity.ok(adminService.getAllAdmins(pageNumber, pageSize));
	}
	
	@PostMapping("/admins")
	public ResponseEntity<AdminDto> addNewCustomer(@RequestBody AdminDto adminDto) {
		return ResponseEntity.ok(adminService.addAdmin(adminDto));
	}
	
	@PutMapping("/admins")
	public ResponseEntity<AdminDto> updateCustomer(@RequestBody AdminDto adminDto) {
		return ResponseEntity.ok(adminService.updateAdmin(adminDto));
		
	}
	
	@GetMapping("/admins/{adminId}")
	public ResponseEntity<AdminDto> getCustomerById(@PathVariable int adminId){
		return ResponseEntity.ok(adminService.getAdminById(adminId));
	}
}
