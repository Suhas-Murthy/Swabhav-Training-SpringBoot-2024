package com.aurionpro.bank.service;

import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.aurionpro.bank.dto.AccountDto;
import com.aurionpro.bank.entity.Account;
import com.aurionpro.bank.entity.Customer;
import com.aurionpro.bank.repository.AccountRepository;
import com.aurionpro.bank.repository.CustomerRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private CustomerRepository customerRepo;
	
	   @Autowired
	    private JavaMailSender javaMailSender;
	   
	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

	public Account toAccountMapper(AccountDto accountDto) {
		Account account = new Account();
		account.setAccountBalance(accountDto.getAccountBalance());
		return account;
	}

	public AccountDto toAccountDtoMapper(Account account) {
		AccountDto accountDto = new AccountDto();
		accountDto.setAccountNumber(account.getAccountNumber());
		accountDto.setAccountBalance(account.getAccountBalance());
		accountDto.setCustomerId(account.getCustomer().getCustomerId());
		return accountDto;
	}

	@Override
	public Page<AccountDto> getAllAccounts(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Account> accountPage = accountRepo.findAll(pageable);
		return accountPage.map(this::toAccountDtoMapper);
	}

	@Override
	public AccountDto addAccount(AccountDto accountDto) {
		long accountNumber = generateUniqueAccountNumber();
		System.out.println("acc" + accountNumber);
		accountDto.setAccountNumber(accountNumber);

		Customer customer = customerRepo.findById(accountDto.getCustomerId())
				.orElseThrow(() -> new RuntimeException("Customer Not Found"));

		Account account = toAccountMapper(accountDto);
		account.setCustomer(customer);
		account.setAccountNumber(accountNumber);

		account = accountRepo.save(account);

		account.setCustomer(customer);
		System.out.println(customer.getCustomerId());
	    // Send email to customer after account creation
	    sendAccountCreationEmail(customer, accountNumber);
		return toAccountDtoMapper(account);
	}

private void sendAccountCreationEmail(Customer customer, long accountNumber) {
    // Fetch the email address of the customer from the associated User entity
    String customerEmail = customer.getUser().getUsername();  // Assuming username is the email

    // Compose the email subject and body
    String subject = "Your New Bank Account Details";
    String body = String.format("""
        Dear %s %s,
\n
        Your new account has been successfully created with account number ending in %d.
    	Account Number: %d
\n        Thank you for banking with us.
\n
        Best regards,
        AurionSwabhavBank
        """, customer.getCustomerFirstName(), customer.getCustomerLastName(), accountNumber % 10000, accountNumber); // Only showing last 4 digits

    // Use JavaMailSender to send the email
    try {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(customerEmail);
        helper.setSubject(subject);
        helper.setText(body, true);  // 'true' indicates HTML content if needed

        javaMailSender.send(message);
        System.out.println("Account creation email sent to " + customerEmail);
    } catch (MessagingException e) {
        System.err.println("Failed to send account creation email: " + e.getMessage());
    }
        // Handle exception or log the error
    }



	@Override
	public AccountDto updateAccount(AccountDto accountDto) {
		Account account = toAccountMapper(accountDto);
		account.setAccountNumber(accountDto.getAccountNumber());

		account = accountRepo.save(account);

		return toAccountDtoMapper(account);
	}

	@Override
	public AccountDto getAcountById(long accountNumber) {
		Account account = null;
		Optional<Account> optionalAccount = accountRepo.findById(accountNumber);

		if (!optionalAccount.isPresent()) {
			return null;
		}
		account = optionalAccount.get();
		return toAccountDtoMapper(account);
	}

	// Method to generate a unique account number
	private long generateUniqueAccountNumber() {
		Random random = new Random();
		long accountNumber;
		do {
			accountNumber = 100000000L + random.nextLong(900000000); // Generates a 9-digit number
		} while (accountRepo.existsByAccountNumber(accountNumber));
		return accountNumber;
	}

}
