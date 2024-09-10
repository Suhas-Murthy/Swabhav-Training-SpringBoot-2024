package com.aurionpro.bank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class RegistrationDto {
	@NotBlank(message = "Username is mandatory")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email address, email should contain @, . , with proper domain name")
	private String username;
	@NotBlank(message = "Password is mandatory")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{9,}$", message = "Password must be at least 9 characters long, contain at least one uppercase letter, one lowercase letter, one number, and one special character")
	private String password;
	@Pattern(regexp = "ROLE_ADMIN|ROLE_CUSTOMER", message = "Role must be either ROLE_ADMIN or ROLE_CUSTOMER")
    private String role;
	@NotBlank(message = "First name is mandatory")
	private String customerFirstName;
	@NotBlank(message = "Last name is mandatory")
	private String customerLastName;
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number, must be 10 digits")
	private String mobileNumber;
}
