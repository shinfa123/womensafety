package com.collage.womensafety.model;

import java.io.Serializable;
/*
 * Used for Authetication Response
 */
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1L;
	private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
