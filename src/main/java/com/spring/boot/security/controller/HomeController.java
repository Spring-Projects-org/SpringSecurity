package com.spring.boot.security.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.Statement;
import com.spring.boot.security.dao.UserRepository;
import com.spring.boot.security.utils.SecurityUtils;

import ch.qos.logback.classic.Logger;

@Controller
public class HomeController {
	
	@Autowired
	DataSource datasource;
	
	@RequestMapping(value="/")
	public String defaultHome()
	{
		
		
		return "home";
		
	}
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(Model model) throws SQLException
	{
		
		SecurityUtils securityUtils=new SecurityUtils();
		String username=securityUtils.getUserName();
		/*//The SecurityContextHolder is a helper class that provides access to the security contex
		In order to get the current username, you first need a SecurityContext, which is obtained from the SecurityContextHolder. 
		This  SecurityContext  kepy the user details in an Authentication object,which can be obtained by calling the getAuthentication()  method 
		
		The getPrincipal() method normally returns UserDetails object in Spring Security, which contains all the details of currently logged in user
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}
			This method we have moved in SecurityUtils
			*/
	
		/* Getting data for user last login
		String lastLogin;
		Connection con=datasource.getConnection();
		PreparedStatement stmt=con.prepareStatement("select max(lastLogin) as lastLogin from user_event where username=?");
		stmt.setString(1, SecurityUtils.getUserName());;
		ResultSet rs=stmt.executeQuery();
		while(rs.next())
		{
		lastLogin=rs.getString(1);	
		}*/
        
		System.out.println("username:"+ username);
		model.addAttribute("username", username);
		model.addAttribute("lastLogin", securityUtils.getUserLastLogin(username,datasource));
		
		
		System.out.println("lastLogin"+securityUtils.getUserLastLogin(username,datasource));
		return "home";
		
	}

}
