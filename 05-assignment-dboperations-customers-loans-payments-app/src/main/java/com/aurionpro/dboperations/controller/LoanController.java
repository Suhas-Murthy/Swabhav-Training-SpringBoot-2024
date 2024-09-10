package com.aurionpro.dboperations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dboperations.entity.Loan;
import com.aurionpro.dboperations.service.LoanService;

@RestController
@RequestMapping("/loanapp")
public class LoanController {

	@Autowired
	private LoanService loanService;

	@GetMapping("/loans")
	public ResponseEntity<List<Loan>> getAllLoans() {
		return ResponseEntity.ok(loanService.getAllLoans());

	}

	@GetMapping("/loans/{loanid}")
	public ResponseEntity<Loan> getLoan(@PathVariable int loanid) {
		return ResponseEntity.ok(loanService.getLoan(loanid));

	}

	@PostMapping("/loans")
	public String addLoan(@RequestBody Loan loan) {

		loanService.addLoan(loan);
		return "Loan Added";

	}

	@PostMapping("/updateloans")
	public String updateLoan(@RequestBody Loan loan) {

		loanService.updateLoan(loan);
		return "Loan Updated";

	}

}
