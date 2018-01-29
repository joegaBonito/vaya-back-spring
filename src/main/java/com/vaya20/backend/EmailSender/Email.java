package com.vaya20.backend.EmailSender;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Email {
	@Id
	@GeneratedValue
	long id;
	String title;
	String username;
	String email;
	String phone;
	String body;
	
	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Email(String title, long id,String username, String email, String phone, String body) {
		super();
		this.id = id;
		this.title = title;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.body = body;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
