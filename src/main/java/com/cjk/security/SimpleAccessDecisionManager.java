package com.cjk.security;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


@Service
public class SimpleAccessDecisionManager implements AccessDecisionManager{
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}
		
		for(ConfigAttribute attr:configAttributes){
			String needRole = StringUtils.trim(attr.getAttribute());
			for(GrantedAuthority grantedAuth : authentication.getAuthorities()){
				String userRole = StringUtils.trim(grantedAuth.getAuthority());
				if(StringUtils.equals(needRole, userRole)){
					return;
				}
			}
		}
		throw new AccessDeniedException("无访问权限");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
