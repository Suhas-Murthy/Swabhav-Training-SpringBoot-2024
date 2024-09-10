package com.aurionpro.dboperations.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {
	@Column(name = "paymentid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentid;
	@Column(name = "paymentdate")
	private Date paymentdate;
	@Column(name = "amount")
	private int amount;
	@Column(name = "paymentmode")
	private PaymentMode paymentmode;
	@Column(name = "paymentstatus")
	private PaymentStatus paymentstatus;

	public Payment() {
		super();
	}

	public Payment(int paymentid, Date paymentdate, int amount, PaymentMode paymentmode, PaymentStatus paymentstatus) {
		super();
		this.paymentid = paymentid;
		this.paymentdate = paymentdate;
		this.amount = amount;
		this.paymentmode = paymentmode;
		this.paymentstatus = paymentstatus;
	}

	public int getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
	}

	public Date getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public PaymentMode getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(PaymentMode paymentmode) {
		this.paymentmode = paymentmode;
	}

	public PaymentStatus getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(PaymentStatus paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	@Override
	public String toString() {
		return "Payment [paymentid=" + paymentid + ", paymentdate=" + paymentdate + ", amount=" + amount
				+ ", paymentmode=" + paymentmode + ", paymentstatus=" + paymentstatus + "]";
	}

}
