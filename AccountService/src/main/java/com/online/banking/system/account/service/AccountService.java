package com.online.banking.system.account.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.banking.system.account.entity.Account;
import com.online.banking.system.account.exception.ResourceNotFoundException;
import com.online.banking.system.account.repository.AccountRepository;
 
@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
	
	//get All Account
	 public List<Account> getAllAccounts() {
	        return accountRepository.findAll();
	    }
	    
	   
	    //create account
	    @Transactional
	    public Account saveAccount(Account account) {
	    	String randomUserId = UUID.randomUUID().toString();
	    	account.setAccountNumberId(randomUserId);
	    	account.setAccountNumber(generateAccountNumber());
	        accountRepository.save(account);
	        return account;
	    }

	    //get account by accountId
	    @Transactional(readOnly = true)
	    public Account getAccountById(String accountId) throws ResourceNotFoundException{
	        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + accountId));
			
	        return account;
	    }
	    
	    
	    //get Id by accountNumber
	    public String getIdByAccount(String accountNumber) throws ResourceNotFoundException{
	        String accountId = accountRepository.findIdByAccountNumber(accountNumber).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + accountNumber));
			
	        return accountId;
	    }

	    //generate 16 digit accountNumber
		public String generateAccountNumber() {
	        
	        Random random = new Random();
	        Integer randomNumber1 = (int) Math.abs(1000 + random.nextInt(9000)); 
	        Integer randomNumber2 = (int) Math.abs(1000 + random.nextInt(9000)); 
	        Integer randomNumber3 = (int) Math.abs(1000 + random.nextInt(9000)); 
	        Integer randomNumber4 = (int) Math.abs(1000 + random.nextInt(9000)); 
	        
	        String randomNumber =randomNumber1.toString()+"-"+randomNumber2.toString()+"-"+randomNumber3.toString()+"-"+randomNumber4.toString();
	         
	        return randomNumber;
	    }
		
		 

	    //Update Account By accountId
	    public Account updateAccount(String accountId, Account updatedAccount) {
	    	
	        Optional<Account> optionalExistingAccount = accountRepository.findById(accountId);
	       
	        if (optionalExistingAccount.isPresent()) {
	            Account existingAccount = optionalExistingAccount.get();
	            // Update properties based on your requirements
	            existingAccount.setAccountType(updatedAccount.getAccountType());
	            
	            if(updatedAccount.getBalance() != null) {
	            existingAccount.setBalance(updatedAccount.getBalance());
	            } 
	            // You may need to update other properties as well

	            // Save the updated account
	            return accountRepository.save(existingAccount);
	        } else {
	            return null; // Account not found
	        }
	    }
	    
	    
	    //Delete Account By accountId
	    public boolean deleteAccount(String accountId) {
	        Optional<Account> optionalAccount = accountRepository.findById(accountId);

	        if (optionalAccount.isPresent()) {
	            accountRepository.deleteById(accountId);
	            return true;
	        } else {
	            return false; // Account not found
	        }
	    }
	    
	    
}
