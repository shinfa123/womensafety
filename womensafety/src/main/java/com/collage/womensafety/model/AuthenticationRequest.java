package com.collage.womensafety.model;

import java.io.Serializable;
/*
 * Used for Authetication Request
 */
public class AuthenticationRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

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

	public AuthenticationRequest() {

	}

	public AuthenticationRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}
}
