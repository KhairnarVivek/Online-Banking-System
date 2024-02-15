package com.online.banking.system.user.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	 
	private String accountNumberId;
	 
	private String accountNumber;
	
	private String accountType;
	
	private Double balance;
	
	@Transient
	private List<Transaction> transaction=new ArrayList<>();


}
