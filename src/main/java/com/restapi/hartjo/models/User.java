package com.restapi.hartjo.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	private Long id;
	private String username;
	private String password;
	private String lastname;
	private String firstname;
	
	public User() {
    }

    public User(Long id) {
        this.id = id;
    }
	
	public User(Long id, String username, String password, String lastname, String firstname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastname = lastname;
    }

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

}
