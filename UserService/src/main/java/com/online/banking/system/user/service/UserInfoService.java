package com.online.banking.system.user.service;
 
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.online.banking.system.user.entity.Account;
import com.online.banking.system.user.entity.Transaction;
import com.online.banking.system.user.entity.UserInfo;
import com.online.banking.system.user.exception.ResourceNotFoundException;
import com.online.banking.system.user.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService{

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(UserInfoService.class);
	
	private String accountUrl = "http://localhost:8083/auth/";
	private String transactionUrl = "http://localhost:8082/auth/transactions/";
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userDetail = userInfoRepository.findByName(username); 

		// Converting userDetail to UserDetails 
		return userDetail.map(UserInfoDetails::new) 
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username)); 
	} 
	
	//Create User
	public UserInfo saveUser(UserInfo userInfo)  throws ResourceNotFoundException, InterruptedException  {
		//generate  unique userId
		String url = accountUrl + "addNewAccount";
        Account account = new Account();
		String randomUserId = UUID.randomUUID().toString();
        userInfo.setPassword(encoder.encode(userInfo.getPassword())); 
        userInfo.setUserId(randomUserId);
        userInfo.setDate(LocalDate.now());
        userInfo.setTime(LocalTime.now());
        account.setBalance(0.0);
        Account response = restTemplate.postForObject(url, account, Account.class);
        userInfo.setAccountNumberId(response.getAccountNumberId());
        return userInfoRepository.save(userInfo);
	}
	
	//Get All User
	public List<UserInfo> getAllUser(){
		return userInfoRepository.findAll();
	}
	
	//Get Single User By UserId
	public UserInfo getUserById(String userId) throws ResourceNotFoundException {
		String url = accountUrl+"/accounts/";
		UserInfo userInfo = userInfoRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given is is not found on server !! : "+userId));
		Account accountOfUser = restTemplate.getForObject(url+userInfo.getAccountNumberId(), Account.class);
		logger.info("{}", accountOfUser);
		userInfo.setAccountDetails(accountOfUser);

		@SuppressWarnings("unchecked")
		ArrayList<Transaction> listOfTransaction = restTemplate.getForObject(transactionUrl+accountOfUser.getAccountNumber(), ArrayList.class);
		accountOfUser.setTransaction(listOfTransaction);
		
		return userInfoRepository.getById(userId);
	}
	
	//Update User
	public UserInfo updateUser(UserInfo userInfo) {
		userInfo.setPassword(encoder.encode(userInfo.getPassword()));
		
		return userInfoRepository.save(userInfo);
	}
	
	//Delete User By userId
	public void deleteUserById(String userId) {
		userInfoRepository.deleteById(userId);
	}
	
	
	
	
}
