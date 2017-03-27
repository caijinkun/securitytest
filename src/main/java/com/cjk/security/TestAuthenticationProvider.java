package com.cjk.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.cjk.security.sso.SSOAuthenticationToken;

public class TestAuthenticationProvider implements AuthenticationProvider{
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
	public static void main(String[] args) {
		System.out.println(UsernamePasswordAuthenticationToken.class.isAssignableFrom(SSOAuthenticationToken.class));
	}
}
