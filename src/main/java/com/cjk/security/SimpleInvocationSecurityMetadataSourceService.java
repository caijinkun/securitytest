package com.cjk.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class SimpleInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource{
	private static Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<>();  

    @PostConstruct
    private void loadResourceDefine() {  
    	
    	String role1 = "ADMIN";
    	String role2 = "SUPER";
    	
    	String permission1 = "";
    	String permission2 = "";
    	String permission3 = "";
    	ConfigAttribute attr1 = new SecurityConfig(permission1);
    	ConfigAttribute attr2 = new SecurityConfig(permission2);
    	ConfigAttribute attr3 = new SecurityConfig(permission3);
    	
    	Set<ConfigAttribute> configAttributeSet1 = new HashSet<>();
    	configAttributeSet1.add(attr1);
    	configAttributeSet1.add(attr2);
    	
    	Set<ConfigAttribute> configAttributeSet2 = new HashSet<>();
    	configAttributeSet2.add(attr1);
    	configAttributeSet2.add(attr2);
    	configAttributeSet2.add(attr3);
    	
    	resourceMap.put(role1, configAttributeSet1);
    	resourceMap.put(role2, configAttributeSet2);
    } 
    
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // object 是一个URL，被用户请求的url。
        FilterInvocation filterInvocation = (FilterInvocation) object;  
        Iterator<String> ite = resourceMap.keySet().iterator();  
        while (ite.hasNext()) {  
            String resURL = ite.next();  
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);  
            if(requestMatcher.matches(filterInvocation.getHttpRequest())) {  
            	return resourceMap.get(resURL);  
            }  
        }  
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return new ArrayList<ConfigAttribute>();
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
