package com.online.banking.system.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.online.banking.system.user.entity.UserInfo;

import java.util.List;
import java.util.Optional; 

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> { 
	
	Optional<UserInfo> findByName(String username);

	@Query(value = "SELECT * FROM userinfo WHERE userId = ?1", nativeQuery = true)
	Optional<UserInfo> findById(String userId); 
		
	@Query(value = "SELECT * FROM userinfo WHERE email = ?1", nativeQuery = true)
	public List<UserInfo> getUserExits(String email);
	
	
	@Query(value = "SELECT * FROM userinfo WHERE userId = ?1", nativeQuery = true)
	public UserInfo getById(String userId);
	
	
	@Query(value = "DELETE FROM userinfo WHERE userId = ?1", nativeQuery = true)
	public UserInfo deleteById(String userId);
	
}
