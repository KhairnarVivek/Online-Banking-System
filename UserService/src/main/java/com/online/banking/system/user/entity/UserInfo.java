package com.online.banking.system.user.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="userinfo", 
uniqueConstraints=
@UniqueConstraint(columnNames={"userId","email"})
)
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
	
	@JsonIgnore
	private String accountNumberId;
	
	@Transient
	private Account accountDetails;



	
}
