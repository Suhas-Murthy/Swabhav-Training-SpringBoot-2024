package com.aurionpro.jpacrud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "students")
@RequiredArgsConstructor   // on the basis of constructor it will provide constructors
//@NoArgsConstructor //- default constructor is added, RequiredArgsConstructor and NoArgsConstructor cannot be used together
@AllArgsConstructor //Constructor gives constructors with all the parameters
@Getter   //generate getters
@Setter		//generate setters
@ToString		//generate toString
//@Data  - getter setter all argumnet contructor
public class Student {
	@Column(name = "rollnumber")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rollnumber;
	@Column(name = "name")
	private String name;
	@Column(name = "age")
	private int age;
	
	
}
