package com.collage.womensafety.model;

import java.io.Serializable;
/*
 * Used for Authetication Response
 */
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1L;
	private final String jwt;
	private boolean isAdmin;

    public String getJwt() {
        return jwt;
    }

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public AuthenticationResponse(String jwt, boolean isAdmin) {
		super();
		this.jwt = jwt;
		this.isAdmin = isAdmin;
	}
    
	
    
}
