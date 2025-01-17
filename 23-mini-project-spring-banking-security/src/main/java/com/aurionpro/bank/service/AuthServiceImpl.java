package com.aurionpro.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aurionpro.bank.dto.LoginDto;
import com.aurionpro.bank.dto.RegistrationDto;
import com.aurionpro.bank.entity.Customer;
import com.aurionpro.bank.entity.Role;
import com.aurionpro.bank.entity.User;
import com.aurionpro.bank.exceptions.UserApiException;
import com.aurionpro.bank.repository.CustomerRepository;
import com.aurionpro.bank.repository.RoleRepository;
import com.aurionpro.bank.repository.UserRepository;
import com.aurionpro.bank.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public User register(RegistrationDto registration) {
		if(userRepo.existsByUsername(registration.getUsername()))
			throw new UserApiException(HttpStatus.BAD_REQUEST,"User already exists");
		
		User user = new User();
		user.setUsername(registration.getUsername());
		
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		List<Role> roles = new ArrayList<Role>();
		
		Role userRole = roleRepo.findByRoleName(registration.getRole()).get();
		roles.add(userRole);
		user.setRoles(roles);
		// Create Customer entity
	    Customer customer = new Customer();
	    customer.setCustomerFirstName(registration.getCustomerFirstName());
	    customer.setCustomerLastName(registration.getCustomerLastName());
	    customer.setMobileNumber(registration.getMobileNumber());
	    
	    // Associate User with Customer
	    customer.setUser(user);
	    
	    // Save User (this should also save the associated Customer due to cascade settings)
	    userRepo.save(user);
	    
	    // Save Customer (optional depending on your cascading configuration)
	    customerRepo.save(customer);
		return user;
//		return userRepo.save(user);
	}

	@Override
	public String login(LoginDto loginDto) {
	    try {
	        Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String token = tokenProvider.generateToken(authentication);

	        return token;
	    } catch (BadCredentialsException e) {
	        throw new UserApiException(HttpStatus.NOT_FOUND, "Username or Password is incorrect");
	    }
	}

}
