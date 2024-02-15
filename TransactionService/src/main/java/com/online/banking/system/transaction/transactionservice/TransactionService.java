package com.online.banking.system.transaction.transactionservice;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.online.banking.system.transaction.entity.Account;
import com.online.banking.system.transaction.entity.Transaction;
import com.online.banking.system.transaction.exception.ResourceNotFoundException;
import com.online.banking.system.transaction.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	private final String accountUrl = "http://localhost:8083/auth/";
	
	//Get All Transaction
	public List<Transaction> getAllTransaction(){
		return transactionRepository.findAll();
	}
	
	//Get Single Transaction By transactionId
	public Transaction getTransactionById(String transactionId) {
		return transactionRepository.getById(transactionId);
	}
	
	
    //Send Money to Another Account
    @Transactional
    public Transaction sendMoney(String fromAccountId, String toAccountId, Transaction transaction1) throws ResourceNotFoundException {
        
    	 
    	
    	//sender's bank account details
    	Account fromAccount = restTemplate.getForObject("http://localhost:8083/auth/accounts/"+fromAccountId, Account.class);
    	
    	
    	//receiver's bank account details
    	Account toAccount = restTemplate.getForObject("http://localhost:8083/auth/accounts/"+toAccountId, Account.class);
    	
    	
    	
        //Taking Old Balance For Comparison
        Double oldBalance = fromAccount.getBalance();
        
        if(fromAccount.getBalance()> transaction1.getAmount()) {
        	//deducting the money from sender's bank account
	        fromAccount.setBalance(fromAccount.getBalance() - transaction1.getAmount());
	        
	        //checking balance deduct or not
	        if(oldBalance - transaction1.getAmount() == fromAccount.getBalance()) {
	        	
	        	
	        	toAccount.setBalance(toAccount.getBalance() + transaction1.getAmount());
	        	
	        	
	        	String url = accountUrl + "/updateAccount/";
	        	
	        	String randomId = UUID.randomUUID().toString();
	        	
	        	Transaction transaction = new Transaction();
	        	
	        	transaction.setTransactionId(randomId);
	        	transaction.setAmount(transaction1.getAmount());
	        	transaction.setFromAccount(fromAccount.getAccountNumber());
	        	transaction.setToAccount(toAccount.getAccountNumber());
	        	transaction.setDate(LocalDate.now());
	        	transaction.setTime(LocalTime.now());
	        	transaction.setDescription(transaction1.getDescription());
	        	transaction.setRemainingBalance(fromAccount.getBalance());
	        	
	        	
	        	restTemplate.put(url+fromAccount.getAccountNumberId(), fromAccount, Account.class);
	        	restTemplate.put(url+toAccount.getAccountNumberId(), toAccount, Account.class);
	        	
	        	transactionRepository.save(transaction);
	        	//Adding money to receivers bank account
	        	return transaction;
	        }
	        else {
	        	//if sender's money got deduct but receiver's did not get money then this logic will run
	        	fromAccount.setBalance(fromAccount.getBalance() + transaction1.getAmount());
	        	return null;
		        
	        }   
        }
        else {
        	return null;
        }
        
        
    }

	 
    public List<Transaction> getAllTransactionByAccountNumber(String accountNumber){
    	return transactionRepository.getAllTransactionByFromAccountNumber(accountNumber);
    }
  
	
}
