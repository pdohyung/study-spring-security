package com.example.spring_security_chap2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig {
	//UserDetailsService를 재정의하면 PasswordEncoder도 선언해야한다.
	@Bean
	public UserDetailsService userDetailsService(){
		var userDetailService = new InMemoryUserDetailsManager();

		var user = User.withUsername("dohyung")
			.password("12345")
			.authorities("read")
			.build();

		userDetailService.createUser(user);

		return userDetailService;
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
}
