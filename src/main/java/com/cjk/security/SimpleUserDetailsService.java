package com.cjk.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cjk.domain.User;
import com.cjk.service.UserService;

public class SimpleUserDetailsService implements UserDetailsService{
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByUsername(username);
		if(null == user){
			throw new UsernameNotFoundException("该用户不存在");
		}
		
		return new SecurityUser(user);
	}
	
}
