package com.cjk.security;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

@Component
public class SimpleSecurityFilter extends AbstractSecurityInterceptor implements Filter {
    private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;  
      
    @Autowired  
    private SimpleAccessDecisionManager accessDecisionManager;  
      
    @Autowired  
    private AuthenticationManager authenticationManager;  
      
      
    @PostConstruct  
    public void init(){  
        super.setAuthenticationManager(authenticationManager);  
        super.setAccessDecisionManager(accessDecisionManager);
        filterInvocationSecurityMetadataSource = new DefaultFilterInvocationSecurityMetadataSource(getRequestMap());
    }  
      
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}
  
    @Override
    public Class<? extends Object> getSecureObjectClass(){  
        return FilterInvocation.class;  
    }  
  
    public void invoke(FilterInvocation fi) throws IOException, ServletException{  
        InterceptorStatusToken  token = super.beforeInvocation(fi);  
        try{  
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());  
        }finally{  
            super.afterInvocation(token, null);  
        }  
          
    }  
          
    @Override  
    public SecurityMetadataSource obtainSecurityMetadataSource(){  
        return this.filterInvocationSecurityMetadataSource;  
    }  
    
    @Override
    public void destroy(){  
    }
    
    @Override
    public void init(FilterConfig filterconfig) throws ServletException{  
    }
    
    protected LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> getRequestMap(){
    	LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<>();
    	
    	return requestMap;
    }
}
