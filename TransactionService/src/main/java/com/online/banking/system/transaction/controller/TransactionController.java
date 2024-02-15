package com.online.banking.system.transaction.controller;

import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.banking.system.transaction.entity.Transaction;
import com.online.banking.system.transaction.exception.ResourceNotFoundException;
import com.online.banking.system.transaction.transactionservice.TransactionService;

@RestController
@RequestMapping("/auth")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	
	//get all transaction
	@GetMapping("/transactions")
	public ResponseEntity<List<Transaction>> getAllTransaction(){
		List<Transaction> allTransaction = transactionService.getAllTransaction();
		return ResponseEntity.ok(allTransaction);
	}
	
	//get single transaction by transactionId
	@GetMapping("/singleTransactions/{transactionId}")
	public ResponseEntity<Transaction> getSingleTransaction(@PathVariable String transactionId){
		Transaction transaction = transactionService.getTransactionById(transactionId);
		return ResponseEntity.ok(transaction);
	}
	
	//send money to another user
	@PostMapping("/sendMoney/{fromAccountId}/to/{toAccountId}")
	public ResponseEntity<Transaction> createTransaction(@PathVariable String fromAccountId, @PathVariable String toAccountId, @RequestBody Transaction transaction) throws ResourceNotFoundException{
		
		Transaction transaction1 = new Transaction();
		Transaction transaction2 = new Transaction();
		transaction1 = transactionService.sendMoney(fromAccountId, toAccountId, transaction);
		transaction2.setDescription("Transaction Fail");
		//System.out.println(transactionService.sendMoney(fromAccountId, toAccountId, transaction));
		if( transaction1 !=  null) {
			
			return ResponseEntity.status(HttpStatus.SC_SUCCESS).body(transaction1); 
			
		}
		else {
			return ResponseEntity.status(HttpStatus.SC_EXPECTATION_FAILED).body(transaction2);
		}
		 
	}
	
	@GetMapping("/transactions/{accountNumber}")
	public ResponseEntity<List<Transaction>> getAllTransaction(@PathVariable String accountNumber){
		
		List<Transaction> listOfTransaction = transactionService.getAllTransactionByAccountNumber(accountNumber);
		return ResponseEntity.ok(listOfTransaction);
	}
	
}
