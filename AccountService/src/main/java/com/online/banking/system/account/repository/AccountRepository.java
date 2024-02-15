package com.online.banking.system.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.online.banking.system.account.entity.Account;
import java.util.Optional; 

@Repository
public interface AccountRepository extends JpaRepository<Account, String> { 
	
	Optional<Account> findById(String userId); 	
	
	@Query(value = "SELECT * FROM account WHERE accountNumberId = ?1", nativeQuery = true)
	public Account getById(String accountNumberId);
	
//	@Query(value = "DELETE FROM account WHERE accountNumberId = ?1", nativeQuery = true)
//	public void deleteById(String accountNumberId);

	@Query(value="SELECT accountNumberId FROM account WHERE accountNumber = ?1", nativeQuery = true)
	public Optional<String> findIdByAccountNumber(String accountNumber);
	
}
