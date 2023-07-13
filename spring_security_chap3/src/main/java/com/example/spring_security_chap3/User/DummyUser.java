package com.example.spring_security_chap3.User;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DummyUser implements UserDetails {
	@Override
	public String getUsername(){
		return "dohyung";
	}

	@Override
	public String getPassword(){
		return "12345";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return List.of(() -> "READ");
	}

	@Override
	public boolean isAccountNonExpired(){
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired(){
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
