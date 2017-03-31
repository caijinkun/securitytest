package com.cjk.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cjk.security.SecurityUser;

public class UserUtils {
	public static SecurityUser getLoginUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication instanceof UsernamePasswordAuthenticationToken){
			UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
			SecurityUser user = (SecurityUser)token.getPrincipal();
			return user;
		}
		return null;
	}
}
