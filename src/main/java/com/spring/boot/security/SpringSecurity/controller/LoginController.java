package com.spring.boot.security.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;

@Controller
public class LoginController {
	
	
	@RequestMapping(value="/validate",method=RequestMethod.GET)
	public String home()
	{
		System.out.println("Logged In");
		
		return "home";
		
	}

}
