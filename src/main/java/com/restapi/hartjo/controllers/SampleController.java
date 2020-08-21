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
import com.restapi.hartjo.models.User;

@RestController
@RequestMapping("/sample")
@ComponentScan("app.restapi.hartjo.configuration")
public class SampleController {
	
	private long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10; // 10 days
    private String secret = "ThisIsASecret";
    private String tokenPrefix = "Bearer ";
    
    @Autowired
    EbeanServer server;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/jwt")
	public String jwt() {
		
		String JWT = Jwts.builder()
	             .setSubject("rain")
	             .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
	             .signWith(SignatureAlgorithm.HS512, secret)
	             .compact();
	         return tokenPrefix + JWT;
	}
	
	@RequestMapping("/user")
	public List<User> user() {
		
		return server.find(User.class).findList();

	}

}
