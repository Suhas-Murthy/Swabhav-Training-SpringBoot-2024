package com.aurionpro.mapping.entity;

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
@Table(name = "salaryaccount")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class SalaryAccount {
	@Column(name = "accountNumber")
	@Id
	private String accountNumber;

	@Column(name = "accountHolderName")
	private String accountHolderName;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "bankid")
	private Bank bank;

}
