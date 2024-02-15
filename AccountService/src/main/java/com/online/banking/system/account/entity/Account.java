package com.online.banking.system.account.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="account", 
uniqueConstraints=
@UniqueConstraint(columnNames={"accountNumberId","accountNumber"})
)
public class Account {

	@Id
	private String accountNumberId;
	
	@Column(unique = true)
	private String accountNumber;
	
	private String accountType;
	
	private Double balance;
	
	//private Long userId;
	
	//private List<Transaction> sentTransaction;
	
	//private List<Transaction> receivedTransaction;
	
	@Transient
	@OrderBy("timestamp ASC")
	private List<Transaction> transaction=new ArrayList<>();

	
}
