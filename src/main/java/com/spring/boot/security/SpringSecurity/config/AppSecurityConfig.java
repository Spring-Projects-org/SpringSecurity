package com.spring.boot.security.SpringSecurity.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub
		List<UserDetails> users=new ArrayList<>();
		users.add(
				User
				.withDefaultPasswordEncoder()
				.username("rahat")
				.password("1234")
				.roles("USER")
				.build());
		
		return new InMemoryUserDetailsManager(users);
	}

	
	
	

}
