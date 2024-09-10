package com.aurionpro.bank.exceptions;

public class SameAccountTransferNotAllowedException extends RuntimeException {
	public String getMessage() {
		return "Transfering to the same accounts is prohibited hence selct appropriate option of either credit or debit instead!!!";
	}
}
