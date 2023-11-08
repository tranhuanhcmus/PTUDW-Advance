package com.example.accessData.User;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



import jakarta.validation.Valid;



@RestController
public class UserController {
	
	private UserRepository userRepository;
	
	
	public UserController(UserRepository user) {
		super();
		this.userRepository =  user;
	}
	
	

//	@GetMapping("/user")
//	public Boolean checkUser(@AuthenticationPrincipal UserDetails userDetails) {
//		
//		String username = userDetails.getUsername();
//		String password = userDetails.getPassword();
//		
//		System.out.println(username);
//		System.out.println(password);
//		
//		return userRepository.existsByUserNameAndPassword(username, password);
//		
//	}

}
