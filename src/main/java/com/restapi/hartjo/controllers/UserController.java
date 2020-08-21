package com.restapi.hartjo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping("/login")
	public HashMap<String, Object> login() {
		HashMap<String, Object> map = new HashMap<>();
		
		List<User> user = server.find(User.class).where().like("id", "1").findList();
		map.put("user", user);
		map.put("page", 1);
		System.out.println(jwtTokenProvider.generateJwt("admin"));
		
		return map;
	}
	
	
	@RequestMapping("/sample")
	public String sample() {
		
		return "sample";
	}
	
}
