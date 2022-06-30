package com.sofrecom.stage.services;

import org.springframework.stereotype.Service;

import com.sofrecom.stage.dto.RegisterRequest;
import com.sofrecom.stage.models.UserInformation;
import com.sofrecom.stage.repository.IUtilidateurRepo;



@Service
public class AuthService {
	
	private IUtilidateurRepo userRepo ; 
	
	public void signup (RegisterRequest registerRequest) {
		UserInformation user = new UserInformation();
		user.setNom(registerRequest.getUserName());
		user.setPassword(registerRequest.getPassword());
		user.setEmail(registerRequest.getEmail());
		userRepo.save(user);
	}
	
}
