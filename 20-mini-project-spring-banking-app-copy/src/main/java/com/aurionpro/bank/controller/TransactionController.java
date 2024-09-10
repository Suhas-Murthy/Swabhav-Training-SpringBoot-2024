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

import com.aurionpro.bank.dto.TransactionDto;
import com.aurionpro.bank.exceptions.InsufficientBalanceException;
import com.aurionpro.bank.service.TransactionService;

@RestController
@RequestMapping("bankapp")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/transactions")
	public ResponseEntity<Page<TransactionDto>> getAllTransactions(@RequestParam int pageNumber,
			@RequestParam int pageSize) {
		return ResponseEntity.ok(transactionService.getAllTransactions(pageNumber, pageSize));
	}

	@PostMapping("/transactions")
	public ResponseEntity<TransactionDto> addNewTransaction(@RequestBody TransactionDto transactionDto) {
		return ResponseEntity.ok(transactionService.addTransaction(transactionDto));
	}

	@PutMapping("/transactions")
	public ResponseEntity<TransactionDto> updateTransaction(@RequestBody TransactionDto transactionDto) {
		return ResponseEntity.ok(transactionService.updateTransaction(transactionDto));
	}

	@GetMapping("/transactions/{transactionId}")
	public ResponseEntity<TransactionDto> getTransactionById(@PathVariable int transactionId) {
		return ResponseEntity.ok(transactionService.getTransactionById(transactionId));
	}

	@PostMapping("/transactions/credit")
	public ResponseEntity<TransactionDto> credit(@RequestBody TransactionDto transactionDto) {
		TransactionDto creditTransaction = transactionService.credit(transactionDto);
		return ResponseEntity.ok(creditTransaction);
	}

	@PostMapping("/transactions/debit")
	public ResponseEntity<TransactionDto> debit(@RequestBody TransactionDto transactionDto) {
		TransactionDto debitTransaction = transactionService.debit(transactionDto);
		return ResponseEntity.ok(debitTransaction);

	}

	@PostMapping("/transactions/transfer")
	public ResponseEntity<TransactionDto> transfer(@RequestBody TransactionDto transactionDto) {
		try {
			TransactionDto transferTransaction = transactionService.transfer(transactionDto);
			return ResponseEntity.ok(transferTransaction);
		} catch (InsufficientBalanceException e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

}
