package com.spring.boot.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.spring.boot.security.dao.UserRepository;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

	@Value("${login.user}")
	private String loginUser;
	@Value("${login.password}")
	private String loginPassword;
	@Value("${user.role}")
	private String userRole;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	@Bean
	public AuthenticationProvider authProvider()	
	{
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		//We need to set service for provider so that it can interact with database
		//We have created custom userdetails service-CustomUserDetailService
		provider.setUserDetailsService(userDetailsService);
		/*Below will set no password encoder meanwhile
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		 */
		//Bcrypt Password encoder
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		
		return provider;
		
	}
	/*
	//If you want to use hard coded username and password
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub
		
		System.out.println("loginUser:"+loginUser);
		List<UserDetails> users=new ArrayList<>();
		users.add(
				User
				.withDefaultPasswordEncoder()
				.username(loginUser)
				.password(loginPassword)
				.roles(userRole)
				.build());
		
		return new InMemoryUserDetailsManager(users);
	}

	*/
	
	

}
