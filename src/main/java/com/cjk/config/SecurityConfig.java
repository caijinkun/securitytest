package com.cjk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.cjk.security.SimpleUserDetailsService;
import com.cjk.security.SuccessHandler;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private SimpleUserDetailsService simpleUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()  
	        	.antMatchers("/static/**", "/index").permitAll()
	        	.anyRequest().authenticated()                
	        	.antMatchers("/hello").hasAuthority("ADMIN")
//	        .and().httpBasic()
	        .and().formLogin()  
	        	.loginPage("/login").permitAll()  
	        	.successHandler(new SuccessHandler())         
	        .and()  
	        	.logout().logoutSuccessUrl("/index")                     
	        	.permitAll()  
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
