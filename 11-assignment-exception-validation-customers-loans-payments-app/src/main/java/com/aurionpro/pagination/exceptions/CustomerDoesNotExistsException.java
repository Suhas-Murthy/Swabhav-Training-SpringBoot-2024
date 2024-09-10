package com.aurionpro.pagination.exceptions;

public class CustomerDoesNotExistsException extends RuntimeException{

	public String getMessage() {
		return "Customer Does Not Exists";
	}
}
