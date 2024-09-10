package com.aurionpro.jpacrud.exceptions;

public class StudentDoesNotExistsException extends RuntimeException {
	
	public String getMessage() {
		return "Student Does Not Exists";
	}

}
