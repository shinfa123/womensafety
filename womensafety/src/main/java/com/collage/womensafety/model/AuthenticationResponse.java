package com.collage.womensafety.model;

import java.io.Serializable;
/*
 * Used for Authetication Response
 */
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1L;
	private final String jwt;
	private boolean isAdmin;
	private Integer userId;

    public String getJwt() {
        return jwt;
    }

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public AuthenticationResponse(String jwt, boolean isAdmin, Integer userId) {
		super();
		this.jwt = jwt;
		this.isAdmin = isAdmin;
		this.userId = userId;
	}
    
	
    
}
