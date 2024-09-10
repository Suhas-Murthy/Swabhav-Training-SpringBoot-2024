package com.aurionpro.bank.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "customers")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Customer {
	@Column(name = "customerId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	@Column(name = "customerFirstName")
	private String customerFirstName;
	@Column(name = "customerLastName")
	private String customerLastName;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;

	@OneToMany(mappedBy = "customer", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JsonIgnore
	private List<Account> accounts;

}
