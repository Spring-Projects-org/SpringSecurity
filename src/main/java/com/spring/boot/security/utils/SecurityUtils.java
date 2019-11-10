package com.spring.boot.security.utils;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class SecurityUtils {
	

	public static String getUserName()
	{
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}
		
		return username;
	}
	
	public static String getUser()
	{
		
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	public String getUserLastLogin(String username,DataSource dataSource)
	{
		String sql="select max(lastLogin) as lastLogin from user_event where username=?";
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		String lastLogin=jdbcTemplate.queryForObject(sql, new Object[] {username}, String.class);		
		return lastLogin;
		
	}

}
