package com.vaya20.backend.Member.domain;

import java.beans.Transient;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vaya20.backend.Member.domain.Role;

@Entity
@Table(name="member")
public class Member implements UserDetails {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@NotNull(message="Email is required")
	@Size(min=1, message="Email is required")
	@Column(name="email")
	private String email;
	
	@Column(name="username")
	private String username;
	
	@NotNull(message="Password is required")
	@Size(min=1, message="Password is required")
	@Column(name="password")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public Member() {
		super();
	}

	public Member(Long id, String email, String username,String password, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.username=username;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	 @JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	 @JsonIgnore
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	 @JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	 @JsonIgnore
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	} 
}
