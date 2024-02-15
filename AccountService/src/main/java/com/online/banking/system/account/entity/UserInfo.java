package com.online.banking.system.account.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

	@Id
	private String userId;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String role;
	
	private String taluka;
	
	private String district;
	
	private String address;
	
	private String branchName;
	
	private LocalDate date;
	
	private LocalTime time;
	
	private String mobileNumber;
	
	private String state;
	 
	
}
