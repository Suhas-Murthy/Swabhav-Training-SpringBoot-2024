package com.aurionpro.mapping.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "banks")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Bank {
	@Column(name = "bankId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId;

	@Column(name = "bankName")
	private String bankName;

	@Column(name = "branch")
	private int branch;

	@Column(name = "ifsccode")
	private int ifsccode;

	@OneToMany(mappedBy = "bank", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private List<SalaryAccount> salaryAccounts;
}
