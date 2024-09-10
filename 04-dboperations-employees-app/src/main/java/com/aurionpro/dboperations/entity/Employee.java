package com.aurionpro.dboperations.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employees")
public class Employee {

	@Column(name="empid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeid;
	@Column(name="name")
	private String name;
	@Column(name = "salary")
	private int salary;
	public Employee(String name, int salary) {
		super();
		this.name = name;
		this.salary = salary;
	}
	
	public Employee() {
		super();
	}
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", name=" + name + ", salary=" + salary + "]";
	}
	
	
	
}
