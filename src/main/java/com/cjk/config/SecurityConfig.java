package com.cjk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cjk.security.SimpleAccessDecisionManager;
import com.cjk.security.SimpleFilterInvocationSecurityMetadataSource;
import com.cjk.security.SimpleSecurityInterceptor;
import com.cjk.security.SimpleUserDetailsService;
import com.cjk.security.TestAuthenticationProvider;
import com.cjk.security.sso.SSOAuthenticationFilter;
import com.cjk.service.ResourceService;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
  	@Autowired
  	private AuthenticationManager authenticationManager;
  	
  	@Bean
  	public SimpleUserDetailsService simpleUserDetailsService(){
  		return new SimpleUserDetailsService();
  	}
  	
  	@Autowired
  	private ResourceService resourceService;
  	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
  	@Override
  	protected void configure(HttpSecurity http) throws Exception {
  		SSOAuthenticationFilter ssoAuthenticationFilter = new SSOAuthenticationFilter(simpleUserDetailsService());
  		SimpleFilterInvocationSecurityMetadataSource simpleFilterInvocationSecurityMetadataSource = 
  				new SimpleFilterInvocationSecurityMetadataSource(resourceService);
  		SimpleAccessDecisionManager simpleAccessDecisionManager = new SimpleAccessDecisionManager();
  		
  		SimpleSecurityInterceptor simpleSecurityInterceptor = new SimpleSecurityInterceptor(
  				simpleAccessDecisionManager, authenticationManager, simpleFilterInvocationSecurityMetadataSource);
  		
//  		http
//  			.addFilterAfter(ssoAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//  			.addFilterBefore(simpleSecurityInterceptor, FilterSecurityInterceptor.class);
  		
  		http
  			.authorizeRequests()  
          	.anyRequest().authenticated()                
  	        .and()
  	        	.csrf().disable()
  	        	.formLogin()
  	        	.loginProcessingUrl("/doLogin")
  	        	.loginPage("/loginPage")
  	        	.usernameParameter("username")
  	        	.passwordParameter("password")
  	        	.successForwardUrl("/index")       
  	        .and()  
  	        	.logout()
  	        	.logoutUrl("/logout")
  	        	.logoutSuccessUrl("/loginPage")                     
  	        	.invalidateHttpSession(true);  
  	}
  	
  	@Override
  	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//  		auth.authenticationProvider(testAuthenticationProvider());
  		auth.userDetailsService(simpleUserDetailsService()).passwordEncoder(bCryptPasswordEncoder());
  	}
  	
  	@Override
  	public void configure(WebSecurity web) throws Exception {
  		web.debug(true)
  			.ignoring().antMatchers("/static/**", "/loginPage");
  		
  	}
  	
//  	@Bean
//  	public TestAuthenticationProvider testAuthenticationProvider(){
//  		return new TestAuthenticationProvider();
//  	}
}
