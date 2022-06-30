package com.sofrecom.stage.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sofrecom.stage.models.UserInformation;
import com.sofrecom.stage.repository.IUtilidateurRepo;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	IUtilidateurRepo userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		UserInformation user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}
	
	public List<UserInformation> getAllUser(long id)  {
		List<UserInformation> user = userRepository.findAll();
				
		return user;
	}

}