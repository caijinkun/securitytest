package com.cjk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.cjk.security.SimpleSecurityFilter;
import com.cjk.security.SimpleUserDetailsService;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private SimpleSecurityFilter simpleSecurityFilter;
	
	@Autowired
	private SimpleUserDetailsService simpleUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl("/index");
		
		http
//			.addFilterBefore(simpleSecurityFilter, FilterSecurityInterceptor.class)
			.authorizeRequests()  
        	.antMatchers("/static/**", "/index", "/login").permitAll()
        	.anyRequest().authenticated()                
	        .and()
	        	.formLogin()  
	        	.loginPage("/login")
	        	.usernameParameter("username").passwordParameter("password")
	        	.successHandler(successHandler)         
	        .and()  
	        	.logout().logoutSuccessUrl("/index")                     
	        	.invalidateHttpSession(true)  
	        .and()  
	        	.rememberMe()                                  
	        	.tokenValiditySeconds(1209600);  
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(simpleUserDetailsService);
	}
}
