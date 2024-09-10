package com.aurionpro.bank.entity;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Transaction {
	@Column(name = "transactionId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	@Column(name = "transactionAmount")
	private double transactionAmount;
	@Column(name = "transactionDate")
	private Date transactionDate;
	@Column(name = "transactionType")
	private TransactionType transactionType;
	@Column(name = "senderAccountNumber")
	private long senderAccountNumber;
	@Column(name = "receiverAccountNumber")
	private long receiverAccountNumber;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "accountNumber")
	private Account account;
}
