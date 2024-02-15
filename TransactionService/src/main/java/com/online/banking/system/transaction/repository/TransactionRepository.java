package com.online.banking.system.transaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.online.banking.system.transaction.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>{

	
	@Query(value="SELECT * FROM transaction WHERE transactionId = ?1 ", nativeQuery = true)
	public Transaction getById(String transactionId);
	
	
	@Query(value="SELECT * FROM transaction WHERE fromAccount = ?1", nativeQuery = true)
	public List<Transaction> getAllTransactionByFromAccountNumber(String accountNumber);
}
