package com.restapi.hartjo.authentications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.restapi.hartjo.models.User;

import io.ebean.EbeanServer;

@Service
public class MyUserDetails implements UserDetailsService {

	@Autowired
    EbeanServer server;

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	
		User user = server.find(User.class).where().like("username", username).findOne();
		
		return org.springframework.security.core.userdetails.User//
		        .withUsername(user.getUsername())//
		        .password(user.getPassword())//
		        .authorities(user.getRole())//
		        .accountExpired(false)//
		        .accountLocked(false)//
		        .credentialsExpired(false)//
		        .disabled(false)//
		        .build();
	}

}
