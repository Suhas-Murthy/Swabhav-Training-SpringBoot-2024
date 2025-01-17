package com.aurionpro.bank.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="customers")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customerId")
	private int customerId;
	
	@Column(name="customerFirstName")
	@NotBlank(message = "First name is mandatory")
	private String customerFirstName;
	
	@Column(name="customerLastName")
	@NotBlank(message = "Last name is mandatory")
	private String customerLastName;
	
	@Column(name="mobileNumber")
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number, must be 10 digits")
	private String mobileNumber;
	
    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;
	
	@OneToMany(mappedBy = "customer", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JsonIgnore
	private List<Account> accounts;
	
	@OneToMany(mappedBy = "customer",cascade = {CascadeType.DETACH,CascadeType.MERGE,  CascadeType.PERSIST,  CascadeType.REFRESH})
	@JsonIgnore
	private List<KYCDocuments> kycDocuments;
}
