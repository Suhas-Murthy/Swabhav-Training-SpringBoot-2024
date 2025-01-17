package com.aurionpro.dboperations.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "students")
public class Student {

	@Column(name = "rollnumber")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//AutoIncrement
	private int rollnumber;
	@Column(name = "name")
	private String name;
	@Column(name = "age")
	private int age;
	
	
	
	
	public Student() {
		super();
	}
	
	
	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public int getRollNumber() {
		return rollnumber;
	}
	public void setRollNumber(int rollNumber) {
		this.rollnumber = rollNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [rollNumber=" + rollnumber + ", name=" + name + ", age=" + age + "]";
	}

	
}


