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

import com.aurionpro.dboperations.entity.Payment;
import com.aurionpro.dboperations.service.PaymentService;

@RestController
@RequestMapping("/paymentapp")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/payments")
	public ResponseEntity<List<Payment>> getAllStudents(){
		return ResponseEntity.ok(paymentService.getAllPayments());	
		
	}
	
	@GetMapping("/payments/{paymentid}")
	public ResponseEntity<Payment> getStudent(@PathVariable int paymentid){
		return ResponseEntity.ok(paymentService.getPayment(paymentid));
		
	}
	
	@PostMapping("/payments")
	public String addStudent(@RequestBody Payment payment) {
		
		paymentService.addPayment(payment);
		return "Payment Added";
		
	}
	
	@PostMapping("/updatepayments")
	public String updateStudent(@RequestBody Payment payment) {
		
		paymentService.updatePayment(payment);
		return "Payment Updated";
		
	}
}
