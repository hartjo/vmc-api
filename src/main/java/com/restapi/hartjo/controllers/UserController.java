package com.restapi.hartjo.controllers;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.hartjo.authentications.JwtTokenProvider;
import com.restapi.hartjo.models.Response;
import com.restapi.hartjo.models.User;

import io.ebean.EbeanServer;

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
	
	@Autowired
    EbeanServer server;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Map<String, Object> payload) {
		HashMap<String, Object> map = new HashMap<>();
		
		User user = server.find(User.class).where().like("username", payload.get("username").toString()).findOne();
		System.out.println(user);
		
		if(user == null) {
			map.put("error", "Username or Password is Invalid!");
			
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}
		
		if(payload.get("username").equals(user.getUsername())) {
			
			
			 System.out.println(user.getPassword().toString());
			if(payload.get("password").equals(user.getPassword())) {
				String token = jwtTokenProvider.generateJwt(user.getUsername());
				
				user.setPassword("");
				
				
				
				map.put("user", user);
				map.put("token", token);
				
				return new ResponseEntity<>(map, HttpStatus.OK);
			} else {
				map.put("error", "Username or Password is Invalid!");
				
				return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
			}
			
			
			
		} else {
			
			map.put("error", "Username Found");
			
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
			
			
		}
	
	
	}
	
	
	@RequestMapping("/sample")
	public String sample() {
		
		return "sample";
	}
	
}
