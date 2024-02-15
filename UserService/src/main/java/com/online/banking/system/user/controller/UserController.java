package com.online.banking.system.user.controller;

import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.banking.system.user.entity.UserInfo;
import com.online.banking.system.user.exception.ResourceNotFoundException;
import com.online.banking.system.user.service.UserInfoService;

@RestController
@RequestMapping("/auth")
public class UserController {

	 
	@Autowired
	private UserInfoService service; 

	
	
	//add New User
		@PostMapping("/addNewUser") 
		public ResponseEntity<UserInfo> addNewUser(@RequestBody UserInfo userInfo) throws ResourceNotFoundException, InterruptedException{ 
			
			UserInfo newUserInfo = service.saveUser(userInfo);
			return ResponseEntity.status(HttpStatus.SC_CREATED).body(newUserInfo); 
		} 

	//get all Users
		@GetMapping("/users")
		@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		public ResponseEntity<List<UserInfo>> getAllUser() {
	        List<UserInfo> allUser = service.getAllUser();
	        return ResponseEntity.ok(allUser);
	    }

		
	//get single User by userId
		@GetMapping("/users/{userId}")
		public ResponseEntity<UserInfo> getSingleUser(@PathVariable String userId) throws ResourceNotFoundException{
			UserInfo userInfo = service.getUserById(userId);
			return ResponseEntity.ok(userInfo);			
			
		}
		
	//Update user by userId
		
		@PutMapping("/updateUser")
		public ResponseEntity<UserInfo> updateSingleUser(@RequestBody UserInfo userInfo){
			UserInfo updateduserInfo = service.updateUser(userInfo);
			return ResponseEntity.status(HttpStatus.SC_ACCEPTED).body(updateduserInfo);
		}
		
	//Delete user by id
		@DeleteMapping("/deleteUser")
		public ResponseEntity<String> deleteUser(@PathVariable String userId){
			service.deleteUserById(userId);
			return ResponseEntity.status(HttpStatus.SC_NO_CONTENT).body("User Successfully Deleted");
		}
}
