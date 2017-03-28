package com.cjk.security;

import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;

public class MyPlaintextPasswordEncoder extends PlaintextPasswordEncoder{
	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		return super.isPasswordValid(encPass, rawPass, salt);
	}
}
