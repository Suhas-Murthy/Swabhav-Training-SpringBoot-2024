package com.aurionpro.bank.exceptions;

public class SameAccountTransferNotAllowedException extends RuntimeException{
	public String getMessage() {
		return "Sender and Receiver account numbers are same \nTransfer to same account is Prohibited \nPlease select appropriate option as credit or debit instead";
	}
}
