package com.online.banking.system.account.controller;

import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.banking.system.account.entity.Account;
import com.online.banking.system.account.service.AccountService;
import com.online.banking.system.account.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/auth")
public class AccountController {

	 
	@Autowired
	private AccountService service; 

	
	
	//add New Account
		@PostMapping("/addNewAccount") 
		public ResponseEntity<Account> addNewAccount(@RequestBody Account account) throws ResourceNotFoundException, InterruptedException{ 
			
			Account newUserInfo = service.saveAccount(account);
			return ResponseEntity.status(HttpStatus.SC_CREATED).body(newUserInfo); 
		} 

	//get all Account
		@GetMapping("/accounts")
		//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		public ResponseEntity<List<Account>> getAllUser() {
	        List<Account> allAccounts = service.getAllAccounts();
	        return ResponseEntity.ok(allAccounts);
	    }

		
	//get single Account by accountNumberId
		@GetMapping("/accounts/{accountNumberId}")
		public ResponseEntity<Account> getSingleAccount(@PathVariable String accountNumberId) throws ResourceNotFoundException{
			Account accouontInfo = service.getAccountById(accountNumberId);
			return ResponseEntity.ok(accouontInfo);			
			
		}
		
	//Update user by userId
		
		@PutMapping("/updateAccount/{accountNumberId}")
		public ResponseEntity<Account> updateSingleAccount(@PathVariable String accountNumberId,@RequestBody Account account){
			Account updatedAccount = service.updateAccount(accountNumberId, account);
			return ResponseEntity.status(HttpStatus.SC_ACCEPTED).body(updatedAccount);
		}
		
	//Delete user by id
		@DeleteMapping("/deleteAccount/{accountNumberId}")
		public ResponseEntity<String> deleteUser(@PathVariable String accountNumberId){
			service.deleteAccount(accountNumberId);
			return ResponseEntity.status(HttpStatus.SC_OK).body("Account Successfully Deleted");
		}
}
