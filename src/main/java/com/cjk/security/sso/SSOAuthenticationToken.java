package com.cjk.security.sso;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class SSOAuthenticationToken extends UsernamePasswordAuthenticationToken{
	private static final long serialVersionUID = 1L;
	
	public SSOAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}
	
	public SSOAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
}
