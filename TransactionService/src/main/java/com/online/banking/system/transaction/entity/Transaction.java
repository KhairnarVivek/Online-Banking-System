package com.online.banking.system.transaction.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

	@Id
	private String transactionId;
	
	private String fromAccount;
	
	private String toAccount;
	
	private Double amount;
	
	private LocalDate date;
	
	private LocalTime time;
	
	private String description;
	 
	private Double remainingBalance;
}
