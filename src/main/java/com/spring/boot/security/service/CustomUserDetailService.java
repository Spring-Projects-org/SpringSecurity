package com.spring.boot.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.boot.security.dao.User;
import com.spring.boot.security.dao.UserRepository;
import com.spring.boot.security.principal.UserPrincipal;


//custom userdetail service which will interact DB to get user details
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    //here we need to get User by username and we need to have UserRepository
	User user=userRepo.findByUsername(username);
    if(user==null)
    	throw new UsernameNotFoundException("User Not Found:404");
	
    return new UserPrincipal(user);
	}

}
