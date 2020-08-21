package com.restapi.hartjo.controllers;

import java.sql.Date;
import java.util.List;

import io.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.ebean.annotation.Transactional;

import com.restapi.hartjo.authentications.JwtTokenProvider;
import com.restapi.hartjo.models.User;

@RestController
@RequestMapping("/sample")
@ComponentScan("app.restapi.hartjo.configuration")
public class SampleController {
    
    @Autowired private JwtTokenProvider jwtTokenProvider; 
    
    @Autowired
    EbeanServer server;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/jwt")
	public String jwt() {
		
		return jwtTokenProvider.generateJwt("hartjo");
	}
	
	@RequestMapping("/user")
	public List<User> user() {
		
		return server.find(User.class).findList();

	}

}
