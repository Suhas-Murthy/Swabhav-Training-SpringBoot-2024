package com.aurionpro.pagination.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "loans")
public class Loan {
	@Column(name="loanid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loadid;
	
	@Column(name="loanamount")
	@NotNull
	private int loanamount;
	
	@Column(name="interestrate")
	@NotNull
	private int interestrate;
	
	@Column(name="loanterm")
	@NotNull
	private int loanterm;
	
	@Column(name="startdate")
	private Date startdate;
	
	@Column(name="enddate")
	private Date enddate;
	
	@Column(name="loanstatus")
	@NotNull
	private LoanStatus loanstatus;

	public Loan() {
		super();
	}

	public Loan(int loadid, int loanamount, int interestrate, int loanterm, Date startdate, Date enddate,
			LoanStatus loanstatus) {
		super();
		this.loadid = loadid;
		this.loanamount = loanamount;
		this.interestrate = interestrate;
		this.loanterm = loanterm;
		this.startdate = startdate;
		this.enddate = enddate;
		this.loanstatus = loanstatus;
	}

	public int getLoadid() {
		return loadid;
	}

	public void setLoadid(int loadid) {
		this.loadid = loadid;
	}

	public int getLoanamount() {
		return loanamount;
	}

	public void setLoanamount(int loanamount) {
		this.loanamount = loanamount;
	}

	public int getInterestrate() {
		return interestrate;
	}

	public void setInterestrate(int interestrate) {
		this.interestrate = interestrate;
	}

	public int getLoanterm() {
		return loanterm;
	}

	public void setLoanterm(int loanterm) {
		this.loanterm = loanterm;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public LoanStatus getLoanstatus() {
		return loanstatus;
	}

	public void setLoanstatus(LoanStatus loanstatus) {
		this.loanstatus = loanstatus;
	}

	@Override
	public String toString() {
		return "Loan [loadid=" + loadid + ", loanamount=" + loanamount + ", interestrate=" + interestrate
				+ ", loanterm=" + loanterm + ", startdate=" + startdate + ", enddate=" + enddate + ", loanstatus="
				+ loanstatus + "]";
	}

}
