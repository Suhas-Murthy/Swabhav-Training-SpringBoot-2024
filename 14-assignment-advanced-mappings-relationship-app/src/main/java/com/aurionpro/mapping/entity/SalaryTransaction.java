package com.aurionpro.mapping.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="salarytransaction")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class SalaryTransaction {

	@Column(name="transactionId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	@Column(name="transactionDate")
	private Date transactionDate;
	@Column(name="amount")
	private double amount;
	@Column(name="status")
	private Status status;
	
	@OneToOne
	@JoinColumn(name="salary-id")
	private Salary salary;
	
}
