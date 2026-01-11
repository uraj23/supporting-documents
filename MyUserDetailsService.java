package com.codeur.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codeur.exception.UserNameNotFoundException;
import com.codeur.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.codeur.model.User user = repository.findByName(username);
		   if(user==null) {
			   throw new UserNameNotFoundException("user not found with username : "+username);
		   }
		return new User(user.getName(),user.getPassword(),Collections.emptyList());
	}

}
