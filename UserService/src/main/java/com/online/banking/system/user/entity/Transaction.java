package com.online.banking.system.user.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

	private String transactionId;
	
	private String fromAccount;
	
	private String toAccount;
	
	private Double amount;
	
	private LocalDate date;
	
	private LocalTime time;
	
	private String description;
	 
	private Double remainingBalance;
}
