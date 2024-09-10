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

import com.aurionpro.bank.dto.AccountDto;
import com.aurionpro.bank.service.AccountService;

@RestController
@RequestMapping("bankapp")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping("/accounts")
	public ResponseEntity<Page<AccountDto>> getAllAccounts(@RequestParam int pageNumber, @RequestParam int pageSize){
		return ResponseEntity.ok(accountService.getAllAccounts(pageNumber, pageSize));
	}
	
	@PostMapping("/accounts")
	public ResponseEntity<AccountDto> addNewAccount(@RequestBody AccountDto accountDto) {
		return ResponseEntity.ok(accountService.addAccount(accountDto));
	}
	
	@PutMapping("/accounts")
	public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto accountDto) {
		return ResponseEntity.ok(accountService.updateAccount(accountDto));
		
	}
	
	@GetMapping("/accounts/{accountNumber}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable int accountNumber){
		return ResponseEntity.ok(accountService.getAcountById(accountNumber));
	}
}
