package com.restapi.hartjo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.hartjo.authentications.JwtTokenProvider;

/**
 * 
 * @author jan.llarenas
 * User Controller
 */

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@RequestMapping("/login")
	public String login() {
		
		System.out.println(jwtTokenProvider.generateJwt("admin"));
		return "sample";
	}
	
	
	@RequestMapping("/sample")
	public String sample2() {
		
		return "sample";
	}
	
}
