package com.aurionpro.pagination.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class Customer {
	@Column(name = "customerid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerid;

	@Column(name = "firstname")
	@NotNull
	@Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters.")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "dateofbirth")
	@NotNull
	private Date dateofbirth;

	@Column(name = "email")
	@NotNull
	@Email(message="Email is improper")
	private String email;

	@Column(name = "mobilenumber")
	@NotNull(message = "Must not be Empty and NULL")
	private Integer mobilenumber;

	public Customer() {
		super();
	}

	public Customer(int customerid, String firstname, String lastname, Date dateofbirth, String email,
			int mobilenumber) {
		super();
		this.customerid = customerid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dateofbirth = dateofbirth;
		this.email = email;
		this.mobilenumber = mobilenumber;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(int mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", dateofbirth=" + dateofbirth + ", email=" + email + ", mobilenumber=" + mobilenumber + "]";
	}

}
