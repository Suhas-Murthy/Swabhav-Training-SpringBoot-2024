package com.aurionpro.bank.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.bank.dto.TransactionDto;
import com.aurionpro.bank.entity.Account;
import com.aurionpro.bank.entity.Transaction;
import com.aurionpro.bank.entity.TransactionType;
import com.aurionpro.bank.exceptions.InsufficientBalanceException;
import com.aurionpro.bank.exceptions.NegativeAmountEnteredException;
import com.aurionpro.bank.exceptions.SameAccountTransferNotAllowedException;
import com.aurionpro.bank.exceptions.ZeroAmountEnteredException;
import com.aurionpro.bank.repository.AccountRepository;
import com.aurionpro.bank.repository.CustomerRepository;
import com.aurionpro.bank.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepo;

	@Autowired
	private AccountRepository accountRepo;
	
    @Autowired
    private EmailSenderServiceForTransaction emailService;

    @Autowired
    private CustomerRepository customerRepo;
	
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

	public Transaction toTransactionMapper(TransactionDto transactionDto) {
		Transaction transaction = new Transaction();
		transaction.setSenderAccountNumber(transactionDto.getSenderAccountNumber());
		transaction.setReceiverAccountNumber(transactionDto.getReceiverAccountNumber());
		transaction.setTransactionAmount(transactionDto.getTransactionAmount());
		transaction.setTransactionDate(transactionDto.getTransactionDate());
		transaction.setTransactionType(transactionDto.getTransactionType());
		return transaction;
	}

	public TransactionDto toTransactionDtoMapper(Transaction transaction) {
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setSenderAccountNumber(transaction.getSenderAccountNumber());
		transactionDto.setReceiverAccountNumber(transaction.getReceiverAccountNumber());
		transactionDto.setTransactionAmount(transaction.getTransactionAmount());
		transactionDto.setTransactionDate(transaction.getTransactionDate());
		transactionDto.setTransactionType(transaction.getTransactionType());

		return transactionDto;
	}

	@Override
	public Page<TransactionDto> getAllTransactions(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Transaction> transactionPage = transactionRepo.findAll(pageable);
		return transactionPage.map(this::toTransactionDtoMapper);
	}

	@Override
	public TransactionDto addTransaction(TransactionDto transactionDto) {
		Transaction transaction = toTransactionMapper(transactionDto);

		transaction = transactionRepo.save(transaction);

		return toTransactionDtoMapper(transaction);
	}

	@Override
	public TransactionDto updateTransaction(TransactionDto transactionDto) {
		Transaction transaction = toTransactionMapper(transactionDto);
		transaction.setTransactionId(transactionDto.getTransactionId());

		transaction = transactionRepo.save(transaction);

		return toTransactionDtoMapper(transaction);
	}

	@Override
	public TransactionDto getTransactionById(int transactionId) {
		Transaction transaction = null;
		Optional<Transaction> optionalTransaction = transactionRepo.findById(transactionId);

		if (!optionalTransaction.isPresent()) {
			return null;
		}
		transaction = optionalTransaction.get();
		return toTransactionDtoMapper(transaction);
	}

	public TransactionDto toTransactionDtoWithIdMapper(Transaction transaction) {
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setTransactionId(transaction.getTransactionId());
		transactionDto.setSenderAccountNumber(transaction.getSenderAccountNumber());
		transactionDto.setReceiverAccountNumber(transaction.getReceiverAccountNumber());
		transactionDto.setTransactionAmount(transaction.getTransactionAmount());
		transactionDto.setTransactionDate(transaction.getTransactionDate());
		transactionDto.setTransactionType(transaction.getTransactionType());

		return transactionDto;
	}

	@Override
	@Transactional
	public TransactionDto credit(TransactionDto transactionDto) {

//		Optional<Account> account1 =  accountRepo.findById(transactionDto.getReceiverAccountNumber());
//				.orElseThrow(()->new AccountNotFoundException("Account Not Found"));
//	Account account = account1.get();

		long accountNumber = transactionDto.getReceiverAccountNumber();
		Account account = accountRepo.findById(accountNumber)
				.orElseThrow(() -> new RuntimeException("Account Not Found"));
		account.setAccountBalance(account.getAccountBalance() + transactionDto.getTransactionAmount());
		accountRepo.save(account);
		
		if (transactionDto.getTransactionAmount() == 0) {
			throw new ZeroAmountEnteredException();
		}

		Transaction transaction = new Transaction();
		transaction.setSenderAccountNumber(accountNumber);
		transaction.setReceiverAccountNumber(accountNumber);
		transaction.setTransactionAmount(transactionDto.getTransactionAmount());
		transaction.setTransactionDate(new Date(System.currentTimeMillis()));
		transaction.setTransactionType(TransactionType.Credit);
		transaction.setAccount(account);
		transactionRepo.save(transaction);

	    String customerEmail = account.getCustomer().getUser().getUsername();
	    String subject = "Credit Transaction Notification";
	    String body = String.format("Dear %s, \na credit of ₹%.2f has been made to your account ending in %d. \n\nThank You for Banking with us.\n\n Best regards,\n AurionSwabhavBank",
	            account.getCustomer().getCustomerFirstName(), transactionDto.getTransactionAmount(), accountNumber);

	    // Send transaction email notification
	    emailService.sendEmail(customerEmail, subject, body);
	        
		return toTransactionDtoWithIdMapper(transaction);
	}

	@Override
	@Transactional
	public TransactionDto debit(TransactionDto transactionDto) {

		long accountNumber = transactionDto.getSenderAccountNumber();
		Account account = accountRepo.findById(accountNumber)
				.orElseThrow(() -> new RuntimeException("Account Not Found"));

		if (transactionDto.getTransactionAmount() < 0) {
			throw new NegativeAmountEnteredException();
		}

		if (transactionDto.getTransactionAmount() == 0) {
			throw new ZeroAmountEnteredException();
		}
		
		if (account.getAccountBalance() < transactionDto.getTransactionAmount()) {
			System.out.println("Hello insufficient exception");
			throw new InsufficientBalanceException();
		}

		account.setAccountBalance(account.getAccountBalance() - transactionDto.getTransactionAmount());
		accountRepo.save(account);

		Transaction transaction = new Transaction();
		transaction.setSenderAccountNumber(accountNumber);
		transaction.setReceiverAccountNumber(accountNumber);
		transaction.setTransactionAmount(transactionDto.getTransactionAmount());
		transaction.setTransactionDate(new Date(System.currentTimeMillis()));
		transaction.setTransactionType(TransactionType.Debit);
		transaction.setAccount(account);
		transactionRepo.save(transaction);
		
		
	    // Fetch customer email from User entity
	    String customerEmail = account.getCustomer().getUser().getUsername();
	    String subject = "Debit Transaction Notification";
	    String body = String.format("Dear %s, \nA debit of ₹%.2f has been made from your account ending in %d. \\\\n\\\\n\\\\n Thank You for Banking with us.\\\\n\\\\n Best regards,\\\\n AurionSwabhavBank",
	            account.getCustomer().getCustomerFirstName(), transactionDto.getTransactionAmount(), accountNumber);

	    // Send transaction email notification
	    emailService.sendEmail(customerEmail, subject, body);

		return toTransactionDtoWithIdMapper(transaction);
	}

	@Override
	@Transactional
	public TransactionDto transfer(TransactionDto transactionDto) {
		Account senderAccountNumber = accountRepo.findById(transactionDto.getSenderAccountNumber())
				.orElseThrow(() -> new RuntimeException("Sender Account Not Found"));

		Account receiverAccountNumber = accountRepo.findById(transactionDto.getReceiverAccountNumber())
				.orElseThrow(() -> new RuntimeException("Receiver Account Not Found"));

		if (transactionDto.getTransactionAmount() < 0) {
			throw new NegativeAmountEnteredException();
		}
		
		if (transactionDto.getTransactionAmount() == 0) {
			throw new ZeroAmountEnteredException();
		}

		if (senderAccountNumber.getAccountBalance() < transactionDto.getTransactionAmount())
			throw new InsufficientBalanceException();
		
		if(senderAccountNumber == receiverAccountNumber) {
			System.out.println("Hello ");
			throw new SameAccountTransferNotAllowedException();
		}

		senderAccountNumber
				.setAccountBalance(senderAccountNumber.getAccountBalance() - transactionDto.getTransactionAmount());

		accountRepo.save(senderAccountNumber);

		receiverAccountNumber
				.setAccountBalance(receiverAccountNumber.getAccountBalance() + transactionDto.getTransactionAmount());
		accountRepo.save(receiverAccountNumber);

		Transaction transaction = new Transaction();
		transaction.setSenderAccountNumber(transactionDto.getSenderAccountNumber());
		transaction.setReceiverAccountNumber(transactionDto.getReceiverAccountNumber());
		transaction.setTransactionAmount(transactionDto.getTransactionAmount());
		transaction.setTransactionDate(new Date(System.currentTimeMillis()));
		transaction.setTransactionType(TransactionType.Transfer);
		transaction.setAccount(senderAccountNumber);
		transactionRepo.save(transaction);
		
		// Fetch sender and receiver emails from User entity
	    String senderEmail = senderAccountNumber.getCustomer().getUser().getUsername();
	    String receiverEmail = receiverAccountNumber.getCustomer().getUser().getUsername();

	    // Prepare email subjects and bodies for sender and receiver
	    String senderSubject = "Transfer Transaction Notification - Amount Debited";
//	    String senderBody = String.format("Dear %s, \nA transfer of ₹%.2f has been debited from your account ending in %d and credited to account ending in %d.\\n\\n\\n Thank You for Banking with us.\\n\\n Best regards,\\n AurionSwabhavBank",
//	    		senderAccountNumber.getCustomer().getCustomerFirstName(), transactionDto.getTransactionAmount(), 
//	            transactionDto.getSenderAccountNumber(), transactionDto.getReceiverAccountNumber());

	    String senderBody = """
	            Dear %s,
	            
	            A transfer of ₹%.2f has been debited from your account ending in %d and credited to account ending in %d.
	            
	            Thank You for Banking with us.
	            
	            Best regards,
	            AurionSwabhavBank
	            """.formatted(senderAccountNumber.getCustomer().getCustomerFirstName(), 
	                          transactionDto.getTransactionAmount(), 
	                          transactionDto.getSenderAccountNumber(),
	                          transactionDto.getReceiverAccountNumber());
	    
	    String receiverSubject = "Transfer Transaction Notification - Amount Credited";
	    
//old method -->
	    //	    String receiverBody = String.format("Dear %s, \nA transfer of ₹%.2f has been credited to your account ending in %d from account ending in %d.\n\n\n Thank You for Banking with us.\n\n Best regards,\\n AurionSwabhavBank",
//	    		receiverAccountNumber.getCustomer().getCustomerFirstName(), transactionDto.getTransactionAmount(), 
//	            transactionDto.getReceiverAccountNumber(), transactionDto.getSenderAccountNumber());
	  
//	    This is new method =>multi-line string
	    
	    String receiverBody = """
	            Dear %s,
	            
	            A transfer of ₹%.2f has been credited to your account ending in %d from account ending in %d.
	            
	            Thank You for Banking with us.
	            
	            Best regards,
	            AurionSwabhavBank
	            """.formatted(receiverAccountNumber.getCustomer().getCustomerFirstName(), 
	                          transactionDto.getTransactionAmount(), 
	                          transactionDto.getReceiverAccountNumber(), 
	                          transactionDto.getSenderAccountNumber());

	    // Send email notifications to both sender and receiver
	    emailService.sendEmail(senderEmail, senderSubject, senderBody);
	    emailService.sendEmail(receiverEmail, receiverSubject, receiverBody);

		return toTransactionDtoWithIdMapper(transaction);
	}

	
	@Override
	public List<TransactionDto> getTransactionByAccountNumber(Account account) {
		
		List<Transaction> transactions = transactionRepo.findByAccount(account);
	
	    // Map each Transaction entity to a TransactionDto
	    List<TransactionDto> transactionDtos = transactions.stream()
	        .map(this::toTransactionDtoWithIdMapper)  // Assuming you have a method to map Transaction to TransactionDto
	        .collect(Collectors.toList());

	    return transactionDtos;
	}


}
