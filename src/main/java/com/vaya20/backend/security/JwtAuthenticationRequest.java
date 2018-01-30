package com.vaya20.backend.security;

import java.io.Serializable;

/**
 * Created by stephan on 20.03.16.
 */
public class  JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String username;
    private String password;
    private String name;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String name, String password) {
        this.setUsername(username);
        this.setPassword(password);
        this.setName(name);
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setName(String name) {
    	this.name = name;
    }

	public String getName() {
		return this.name;
	}
}
