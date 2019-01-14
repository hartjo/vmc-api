package com.restapi.hartjo.controllers;

import java.sql.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/sample")
public class SampleController {
	
	private long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10; // 10 days
    private String secret = "ThisIsASecret";
    private String tokenPrefix = "Bearer ";
	
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

}
