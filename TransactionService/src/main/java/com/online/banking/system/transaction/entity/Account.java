package com.online.banking.system.transaction.entity;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

private String accountNumberId;
	
	@Column(unique = true)
	private String accountNumber;
	
	private String accountType;
	
	private Double balance;
	
	//private Long userId;
	
	//private List<Transaction> sentTransaction;
	
	//private List<Transaction> receivedTransaction;
	
	@Transient
	private List<Transaction> transaction=new ArrayList<>();

}
