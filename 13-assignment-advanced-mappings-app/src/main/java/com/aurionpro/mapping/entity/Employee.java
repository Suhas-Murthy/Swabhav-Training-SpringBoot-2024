package com.aurionpro.mapping.entity;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor 
@Getter  
@Setter		
@ToString
@Table(name="employees")
public class Employee {
	@Column(name="employeeid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeid;
	
	@Column(name="firstname")
	@NotBlank(message = "First name is mandatory")
	private String firstname;
	@Column(name="lastname")
	@NotBlank(message = "Last name is mandatory")
	private String lastname;
	@Column(name="phonenumber")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number, must be 10 digits")
	private String phonenumber;
	@Column(name="email")
	@Email(message="Email Invalid")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$", message = "Invalid email address, email should contain @, . , with proper domain name")
	private String email;
	@Column(name="position") 
	private String position;
	@Column(name="hiredate")
	@PastOrPresent(message = "Date of hiring cannot be in the future")
	private Date hiredate;
	@Column(name="salary")
    @PositiveOrZero(message = "Salary cannot be negative")
	private double salary;
	@Column(name="bankaccountnumber")
//	@Pattern(regexp = "^[0-9]{9,14}$",message="Bank account number must be between 9 and 14 digits")
	private String bankaccountnumber;
	@Column(name="bankifscnumber")
	private String bankifscnumber;
	@Column(name="status")
//	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="clientid")
	private Client client;
	

}
