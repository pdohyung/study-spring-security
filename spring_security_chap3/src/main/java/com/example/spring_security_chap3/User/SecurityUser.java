package com.example.spring_security_chap3.User;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/*
public class SecurityUser implements UserDetails {
	private final User user;

	public SecurityUser(User user){
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return List.of(() -> user.getAuthority());
	}
}
 */
